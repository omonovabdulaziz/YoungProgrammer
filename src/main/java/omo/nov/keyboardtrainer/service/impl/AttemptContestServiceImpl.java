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
    public ResponseEntity<ApiResponse> add(AttemptContestDTO attemptContestDTO) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        if (systemUser.getStatus()) {
            Contest contest = contestRepository.findById(attemptContestDTO.getContestId()).orElseThrow(() -> new NotFoundException("Musobaqa topilmadi"));
            if (contest.getStatus() == Status.JARAYONDA) {
                Optional<AttemptRate> optionalAttemptRate = attemptRateRepository.findByUserIdAndContestId(systemUser.getId(), attemptContestDTO.getContestId());
                if (optionalAttemptRate.isEmpty()) {
                    attemptRateRepository.save(AttemptRate.builder().commonTrue(attemptContestDTO.getTrueLetterCount() - attemptContestDTO.getFalseLetterCount()).startAt(attemptContestDTO.getStartAt()).endAt(attemptContestDTO.getEndAt()).contest(contest).falseLetterCount(attemptContestDTO.getFalseLetterCount()).trueLetterCount(attemptContestDTO.getTrueLetterCount()).user(systemUser).build());
                } else {
                    AttemptRate attemptRate = optionalAttemptRate.get();
                    int i = attemptRate.getTrueLetterCount() - attemptRate.getFalseLetterCount();
                    int i1 = attemptContestDTO.getTrueLetterCount() - attemptContestDTO.getFalseLetterCount();
                    System.out.println("eskisi " + i);
                    System.out.println("yangisi" + i1);
                    if (i1 > i) {
                        attemptRate.setTrueLetterCount(attemptContestDTO.getTrueLetterCount());
                        attemptRate.setFalseLetterCount(attemptContestDTO.getFalseLetterCount());
                        attemptRate.setCommonTrue(i1);
                        attemptRate.setStartAt(attemptContestDTO.getStartAt());
                        attemptRate.setEndAt(attemptContestDTO.getEndAt());
                        attemptRateRepository.save(attemptRate);
                    }
                }
                attemptContestRepository.save(AttemptContest.builder().startAt(attemptContestDTO.getStartAt()).endAt(attemptContestDTO.getEndAt()).contest(contest).falseLetterCount(attemptContestDTO.getFalseLetterCount()).trueLetterCount(attemptContestDTO.getTrueLetterCount()).user(systemUser).build());
                return ResponseEntity.ok(ApiResponse.builder().status(200).message("Ok Saved").build());
            } else {
                throw new MainException("No process or end");
            }
        }
        throw new ForbiddenException("User not confirmed");
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
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        Page<AttemptRateDTO> pageAttempt = attemptRateRepository.findAllByContestIdOrderByCommonTrue(contestId, PageRequest.of(page, size));
        long myPlace = 1L;
        for (AttemptRate attemptRate : attemptRateRepository.findAllByContestIdOrderByCommonTrueDesc(contestId)) {
            if (attemptRate.getUser().equals(systemUser)) {
                break;
            }
            myPlace++;
        }
        return AttemptRateCommon.builder().attemptRateDTOS(pageAttempt).myPlace(myPlace).build();
    }
}
