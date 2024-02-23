package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.config.SecurityConfiguration;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.exception.NotFoundException;
import omo.nov.keyboardtrainer.jwt.JwtProvider;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.UpdateDTO;
import omo.nov.keyboardtrainer.repository.UserRepository;
import omo.nov.keyboardtrainer.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Override

    public User getSelfInformation() {
        return SecurityConfiguration.getOwnSecurityInformation();
    }

    @Override
    public ResponseEntity<ApiResponse> updateConfirm(Long userId, Boolean status) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Bunday user topilmadi"));
        user.setStatus(status);
        userRepository.save(user);
        return ResponseEntity.ok(ApiResponse.builder().message("User status updated").status(200).build());
    }

    @Override
    public ResponseEntity<ApiResponse> updateInformation(UpdateDTO updateDTO) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        if (!systemUser.getStatus()) {
            systemUser.setSurname(updateDTO.getSurname());
            systemUser.setName(updateDTO.getName());
            systemUser.setRegion(updateDTO.getRegion());
            systemUser.setAge(updateDTO.getAge());
        }
        systemUser.setPhoneNumber(updateDTO.getPhoneNumber());
        if (updateDTO.getPasswords() != null) {
            systemUser.setPasswords(passwordEncoder.encode(updateDTO.getPasswords()));
        }
        User user = userRepository.save(systemUser);
        return ResponseEntity.ok(ApiResponse.builder().message("Updated").object(jwtProvider.generateToken(user)).status(200).build());
    }

    @Override
    public Page<User> getUserByStatus(Boolean status, int page, int size) {
        return userRepository.findAllByStatus(status, PageRequest.of(page, size));
    }


}
