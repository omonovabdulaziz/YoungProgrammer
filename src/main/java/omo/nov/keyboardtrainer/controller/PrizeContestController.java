package omo.nov.keyboardtrainer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.PrizeContestDTO;
import omo.nov.keyboardtrainer.service.PrizeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prize")
@RequiredArgsConstructor
public class PrizeContestController {
    private final PrizeService prizeService;


    @Operation(summary = "Add a file to the contest")
    @PostMapping(value = "/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> add(
            @RequestParam Long contestId,
            @RequestParam String description,
            @RequestParam String title,
            @RequestPart MultipartFile multipartFile) {
        return prizeService.add(contestId, description, title, multipartFile);
    }

    @GetMapping("/getAllByContest/{contestId}")
    public List<PrizeContestDTO> getAlLByContest(@PathVariable Long contestId) {
        return prizeService.getAllByContest(contestId);
    }

    @GetMapping("/getById/{id}")
    public PrizeContestDTO getById(@PathVariable Long id) {
        return prizeService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        return prizeService.delete(id);
    }

}
