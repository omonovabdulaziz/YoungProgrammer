package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.exception.ForbiddenException;
import omo.nov.keyboardtrainer.exception.MainException;
import omo.nov.keyboardtrainer.jwt.JwtProvider;
import omo.nov.keyboardtrainer.mapper.UserMapper;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.LoginDTO;
import omo.nov.keyboardtrainer.payload.RegisterDTO;
import omo.nov.keyboardtrainer.repository.UserRepository;
import omo.nov.keyboardtrainer.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<ApiResponse> register(RegisterDTO registerDTO) {
        if (userRepository.existsByPhoneNumber(registerDTO.getPhoneNumber()))
            throw new MainException("Bunday raqamli foydalanuvchi mavjud");
        User user = userRepository.save(userMapper.toEntity(registerDTO));
        return ResponseEntity.ok(ApiResponse.builder().message("User qo'shildi").status(201).object(jwtProvider.generateToken(user)).build());
    }

    @Override
    public ResponseEntity<ApiResponse> login(LoginDTO loginDTO) {
        User user = userRepository.findByPhoneNumber(loginDTO.getPhoneNumber()).orElseThrow(() -> new ForbiddenException("Login yoki parol xato"));
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()))
            throw new ForbiddenException("Login yoki parol xato");
        user.setDeviceIp(loginDTO.getDeviceIp());
        userRepository.save(user);
        return ResponseEntity.ok(ApiResponse.builder().message(user.getSystemRoleName().name()).status(200).object(jwtProvider.generateToken(user)).build());
    }
}
