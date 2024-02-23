package omo.nov.keyboardtrainer.controller;

import jakarta.servlet.http.PushBuilder;
import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.UpdateDTO;
import omo.nov.keyboardtrainer.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/getSelfInformation")
    public User getSelfInformation() {
        return userService.getSelfInformation();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateConfirm/{userId}")
    public ResponseEntity<ApiResponse> updateConfirm(@PathVariable Long userId, @RequestParam Boolean status) {
        return userService.updateConfirm(userId, status);
    }


    @PutMapping("/updateInformation")
    public ResponseEntity<ApiResponse> updateInformation(@RequestBody UpdateDTO updateDTO) {
        return userService.updateInformation(updateDTO);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getUserByStatus")
    public Page<User> getUserBy(@RequestParam Boolean status , @RequestParam int page , @RequestParam int size ) {
        return userService.getUserByStatus(status , page ,size);
    }
}
