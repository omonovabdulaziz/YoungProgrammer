package omo.nov.keyboardtrainer.service;

import jakarta.servlet.http.HttpServletRequest;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.LoginDTO;
import omo.nov.keyboardtrainer.payload.RegisterDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {

    ResponseEntity<ApiResponse> register(RegisterDTO registerDTO);

    ResponseEntity<ApiResponse> login(LoginDTO loginDTO);
}
