package omo.nov.keyboardtrainer.service;

import omo.nov.keyboardtrainer.payload.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface BanUserService {
    ResponseEntity<ApiResponse> add(Long userId);

}
