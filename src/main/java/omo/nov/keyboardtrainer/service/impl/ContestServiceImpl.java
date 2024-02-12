package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.Contest;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.exception.MainException;
import omo.nov.keyboardtrainer.exception.NotFoundException;
import omo.nov.keyboardtrainer.mapper.ContestMapper;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.ContestDTO;
import omo.nov.keyboardtrainer.payload.PageContestDTO;
import omo.nov.keyboardtrainer.repository.ContestRepository;
import omo.nov.keyboardtrainer.service.ContestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
public class ContestServiceImpl implements ContestService {
    private final ContestRepository contestRepository;
    private final ContestMapper contestMapper;

    @Override
    public ResponseEntity<ApiResponse> add(ContestDTO contestDTO) {
        contestRepository.save(contestMapper.toEntity(contestDTO));
        return ResponseEntity.ok(ApiResponse.builder().message("Contest yaratildi").status(201).build());
    }

    @Override
    public Page<PageContestDTO> getPage(int page, int size, Status status) {
        if (status == null) {
            return contestRepository.findAllContests(of(page, size));
        }
        if (status == Status.BOSHLANMAGAN) {
            return contestRepository.findAllContestsByValidity(Status.BOSHLANMAGAN, of(page, size, Sort.by("createdAt").descending()));
        } else if (status == Status.TUGADI) {
            return contestRepository.findAllContestsByValidity(Status.TUGADI, of(page, size, Sort.by("createdAt").descending()));
        } else {
            return contestRepository.findAllContestsByValidity(Status.JARAYONDA, of(page, size, Sort.by("createdAt").descending()));
        }
    }

    @Override
    public PageContestDTO getById(Long id) {
        return contestRepository.findByCustom(id);
    }

    @Override
    public ResponseEntity<ApiResponse> updateById(Long id, ContestDTO contestDTO) {
        Contest contest = contestRepository.findById(id).orElseThrow(() -> new NotFoundException("Topilmadi"));
        contestRepository.save(contestMapper.toEntity(contestDTO, contest));
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("Updated").build());
    }
    @Override
    public ResponseEntity<ApiResponse> deleteById(Long id) {
        try {
            contestRepository.deleteById(id);
            return ResponseEntity.ok(ApiResponse.builder().status(200).message("Deleted").build());
        } catch (Exception e) {
            throw new MainException("O'chirishda xatolik");
        }
    }
    @Override
    public ResponseEntity<ApiResponse> updateStatus(Long contestId, Status status) {
        Contest contest = contestRepository.findById(contestId).orElseThrow(() -> new NotFoundException("Bunday contest topilmadi"));
        contest.setStatus(status);
        contestRepository.save(contest);
        return ResponseEntity.ok(ApiResponse.builder().message("Status o'zgardi").build());
    }
    @Override
    public PageContestDTO getLastContest() {
        return contestRepository.getLastContest();
    }
}
