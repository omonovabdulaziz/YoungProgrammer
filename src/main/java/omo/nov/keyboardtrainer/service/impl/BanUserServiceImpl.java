package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.exception.NotFoundException;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.repository.UserRepository;
import omo.nov.keyboardtrainer.service.BanUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanUserServiceImpl implements BanUserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse> add(Long userId) {
        User banUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Bunday user yoq"));
        banUser.setIsBanned(true);
        userRepository.save(banUser);
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("Ok").build());
    }
}
