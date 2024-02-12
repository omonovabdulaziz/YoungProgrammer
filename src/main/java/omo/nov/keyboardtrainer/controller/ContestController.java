package omo.nov.keyboardtrainer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.ContestDTO;
import omo.nov.keyboardtrainer.payload.PageContestDTO;
import omo.nov.keyboardtrainer.service.ContestService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contest")
@RequiredArgsConstructor
public class ContestController {
    private final ContestService contestService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@Valid @RequestBody ContestDTO contestDTO) {
        return contestService.add(contestDTO);
    }

    @GetMapping("/getContestPage")
    public Page<PageContestDTO> getPage(@RequestParam int page, @RequestParam int size, @RequestParam(required = false) Status status) {
        return contestService.getPage(page, size, status);
    }

    @GetMapping("/getById/{id}")
    public PageContestDTO getById(@PathVariable Long id) {
        return contestService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateById(@PathVariable Long id, @RequestBody ContestDTO contestDTO) {
        return contestService.updateById(id, contestDTO);
    }

    @PutMapping("/updateStatus/{contestId}")
    public ResponseEntity<ApiResponse> updateStatus(@PathVariable Long contestId, @RequestParam Status status) {
        return contestService.updateStatus(contestId, status);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id) {
        return contestService.deleteById(id);
    }


    @GetMapping("/getLastContest")
    public PageContestDTO getLastContest() {
        return contestService.getLastContest();
    }
}