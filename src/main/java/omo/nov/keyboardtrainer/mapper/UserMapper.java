package omo.nov.keyboardtrainer.mapper;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.entity.enums.SystemRoleName;
import omo.nov.keyboardtrainer.payload.RegisterDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User toEntity(RegisterDTO registerDTO) {
        return User.builder()
                .name(registerDTO.getName())
                .deviceIp(registerDTO.getDeviceIp())
                .phoneNumber(registerDTO.getPhoneNumber())
                .surname(registerDTO.getSurname())
                .systemRoleName(SystemRoleName.ROLE_USER)
                .passwords(passwordEncoder.encode(registerDTO.getPassword()))
                .status(true)
                .region(registerDTO.getRegion())
                .age(registerDTO.getAge())
                .build();
    }
}
