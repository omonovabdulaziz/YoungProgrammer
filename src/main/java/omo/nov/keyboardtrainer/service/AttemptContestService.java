package omo.nov.keyboardtrainer.service;

import omo.nov.keyboardtrainer.payload.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.UUID;


public interface AttemptContestService {
    ResponseEntity<ApiResponse> add(AttemptContestDTO attemptContestDTO);


    Page<AttemptContestPage> getAllAttemptByUserAndContestId(Long userId, Long contestId, int page, int size);

    AttemptContestId getById(UUID id);

    AttemptRateCommon getRate(Long contestId , int page , int size);

}
