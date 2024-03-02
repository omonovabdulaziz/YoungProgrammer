package omo.nov.keyboardtrainer.controller;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.service.BanUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ban")
@RequiredArgsConstructor
public class BanUserController {
    private final BanUserService banUserService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestParam Long userId) {
        return banUserService.add(userId);
    }
}
