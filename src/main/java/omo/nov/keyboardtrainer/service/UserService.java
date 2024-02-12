package omo.nov.keyboardtrainer.service;

import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.UpdateDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User getSelfInformation();

    ResponseEntity<ApiResponse> updateConfirm(Long userId , Boolean status);

    ResponseEntity<ApiResponse> updateInformation(UpdateDTO updateDTO);

}
