package omo.nov.keyboardtrainer.service;

import omo.nov.keyboardtrainer.entity.NewsSeenTouch;
import omo.nov.keyboardtrainer.entity.enums.SeenTouch;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.NewSeenTouchDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TouchService {
    ResponseEntity<ApiResponse> add(SeenTouch seenTouch, String deviceIp, List<Long> newsId);

    List<NewSeenTouchDTO> get();

}
