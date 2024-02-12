package omo.nov.keyboardtrainer.service;

import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.PrizeContestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PrizeService {

    ResponseEntity<ApiResponse> add(Long contestId, String description, String title, MultipartFile multipartFiles);

    List<PrizeContestDTO> getAllByContest(Long contestId);

    PrizeContestDTO getById(Long id);

    ResponseEntity<ApiResponse> delete(Long id);
}
