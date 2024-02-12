package omo.nov.keyboardtrainer.service;

import omo.nov.keyboardtrainer.entity.Contest;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.ContestDTO;
import omo.nov.keyboardtrainer.payload.PageContestDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ContestService {
    ResponseEntity<ApiResponse> add(ContestDTO contestDTO);

    Page<PageContestDTO> getPage(int page, int size, Status  status);

    PageContestDTO getById(Long id);

    ResponseEntity<ApiResponse> updateById(Long id, ContestDTO contestDTO);

    ResponseEntity<ApiResponse> deleteById(Long id);


    ResponseEntity<ApiResponse> updateStatus(Long contestId, Status status);

    PageContestDTO getLastContest();
}
