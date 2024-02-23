package omo.nov.keyboardtrainer.controller;


import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.payload.*;
import omo.nov.keyboardtrainer.service.AttemptContestService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/attemptContest")
@RequiredArgsConstructor
public class AttemptContestController {
    private final AttemptContestService attemptContestService;


    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody AttemptContestDTO attemptContestDTO) {
        return attemptContestService.add(attemptContestDTO);
    }

    @GetMapping("/getAllAttemptByUserAndContestId/{userId}/{contestId}")
    public Page<AttemptContestPage> getAllAttemptByUserAndContestId(@PathVariable Long userId, @PathVariable Long contestId, @RequestParam int page, @RequestParam int size) {
        return attemptContestService.getAllAttemptByUserAndContestId(userId, contestId, page, size);
    }

    @GetMapping("/getById")
    public AttemptContestId getById(@RequestParam UUID id) {
        return attemptContestService.getById(id);
    }

    @GetMapping("/rate/{contestId}")
    public AttemptRateCommon getRate(@PathVariable Long contestId, @RequestParam int page, @RequestParam int size) {
        return attemptContestService.getRate(contestId, page, size);
    }

    

}
