package omo.nov.keyboardtrainer.controller;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.enums.SeenTouch;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.NewSeenTouchDTO;
import omo.nov.keyboardtrainer.service.TouchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seenTouch")
@RequiredArgsConstructor
public class TouchNewsController {
    private final TouchService touchService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestParam SeenTouch seenTouch, @RequestParam String deviceIp, @RequestParam List<Long> newsId) {
        return touchService.add(seenTouch, deviceIp, newsId);
    }

    @GetMapping("/get")
    public List<NewSeenTouchDTO> get() {
        return touchService.get();
    }

}
