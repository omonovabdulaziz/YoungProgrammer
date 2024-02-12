package omo.nov.keyboardtrainer.service;

import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.LoginDTO;
import omo.nov.keyboardtrainer.payload.RegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface AuthService {

    ResponseEntity<ApiResponse> register(RegisterDTO registerDTO);

    ResponseEntity<ApiResponse> login(LoginDTO loginDTO);
}
