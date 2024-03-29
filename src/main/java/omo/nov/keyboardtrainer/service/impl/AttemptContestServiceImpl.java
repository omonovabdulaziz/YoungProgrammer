package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.config.SecurityConfiguration;
import omo.nov.keyboardtrainer.entity.AttemptContest;
import omo.nov.keyboardtrainer.entity.AttemptRate;
import omo.nov.keyboardtrainer.entity.Contest;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.exception.ForbiddenException;
import omo.nov.keyboardtrainer.exception.MainException;
import omo.nov.keyboardtrainer.exception.NotFoundException;
import omo.nov.keyboardtrainer.mapper.AttemptContestMapper;
import omo.nov.keyboardtrainer.payload.*;
import omo.nov.keyboardtrainer.repository.AttemptContestRepository;
import omo.nov.keyboardtrainer.repository.AttemptRateRepository;
import omo.nov.keyboardtrainer.repository.ContestRepository;
import omo.nov.keyboardtrainer.service.AttemptContestService;
import omo.nov.keyboardtrainer.util.Encrypter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttemptContestServiceImpl implements AttemptContestService {

    private final AttemptContestRepository attemptContestRepository;
    private final AttemptRateRepository attemptRateRepository;
    private final ContestRepository contestRepository;
    private final AttemptContestMapper attemptContestMapper;

    @Override
    public ResponseEntity<ApiResponse> add(AttemptContestDTO attemptContestDTO) throws Exception {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        Integer trueLetterCount = Encrypter.decrypt(attemptContestDTO.getTrueLetterCount());
        Integer falseLetterCount = Encrypter.decrypt(attemptContestDTO.getFalseLetterCount());
        if (trueLetterCount == null || falseLetterCount == null) throw new ForbiddenException("Forbidden");
        Contest contest = contestRepository.findById(attemptContestDTO.getContestId()).orElseThrow(() -> new NotFoundException("Musobaqa topilmadi"));
        if (contest.getStatus() == Status.JARAYONDA) {
            Optional<AttemptRate> optionalAttemptRate = attemptRateRepository.findByUserIdAndContestId(systemUser.getId(), attemptContestDTO.getContestId());
            if (optionalAttemptRate.isEmpty()) {
                attemptRateRepository.save(AttemptRate.builder().commonTrue(trueLetterCount - falseLetterCount).startAt(attemptContestDTO.getStartAt()).endAt(attemptContestDTO.getEndAt()).contest(contest).falseLetterCount(falseLetterCount).trueLetterCount(trueLetterCount).user(systemUser).build());
            } else {
                AttemptRate attemptRate = optionalAttemptRate.get();
                int i = attemptRate.getTrueLetterCount() - attemptRate.getFalseLetterCount();
                int i1 = trueLetterCount - falseLetterCount;
                if (i1 > i) {
                    attemptRate.setTrueLetterCount(trueLetterCount);
                    attemptRate.setFalseLetterCount(falseLetterCount);
                    attemptRate.setCommonTrue(i1);
                    attemptRate.setStartAt(attemptContestDTO.getStartAt());
                    attemptRate.setEndAt(attemptContestDTO.getEndAt());
                    attemptRateRepository.save(attemptRate);
                }
            }
            attemptContestRepository.save(AttemptContest.builder().startAt(attemptContestDTO.getStartAt()).endAt(attemptContestDTO.getEndAt()).contest(contest).falseLetterCount(falseLetterCount).trueLetterCount(trueLetterCount).user(systemUser).build());
            return ResponseEntity.ok(ApiResponse.builder().status(200).message("Ok Saved").build());
        } else {
            throw new MainException("No process or end");
        }
    }

    @Override
    public Page<AttemptContestPage> getAllAttemptByUserAndContestId(Long userId, Long contestId, int page, int size) {
        return attemptContestRepository.getAllAttemptByUserAndContest(userId, contestId, PageRequest.of(page, size));
    }

    @Override
    public AttemptContestId getById(UUID id) {
        return attemptContestRepository.findById(id).map(attemptContestMapper::toDTO).orElseThrow(() -> new NotFoundException("Not found"));
    }

    @Override
    public AttemptRateCommon getRate(Long contestId, int page, int size) {
        Page<AttemptRateDTO> pageAttempt = attemptRateRepository.findAllByContestIdOrderByCommonTrue(contestId, PageRequest.of(page, size));
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        Long myPlace = 1L;
        if (systemUser.getStatus()) {
            for (AttemptRate attemptRate : attemptRateRepository.findAllByContestIdAndUser_StatusAndFalseLetterCountLessThanOrderByCommonTrueDesc(contestId, true, 13)) {
                if (attemptRate.getUser().equals(systemUser)) break;
                myPlace++;
            }
        } else {
            myPlace = null;
        }
        return AttemptRateCommon.builder().attemptRateDTOS(pageAttempt).myPlace(myPlace).build();
    }

    @Override
    public AttemptRateCommon getRateNotUser(Long contestId, int page, int size) {
        Page<AttemptRateDTO> pageAttempt = attemptRateRepository.findAllByContestIdOrderByCommonTrue(contestId, PageRequest.of(page, size));
        return AttemptRateCommon.builder().attemptRateDTOS(pageAttempt).myPlace(null).build();
    }
}
