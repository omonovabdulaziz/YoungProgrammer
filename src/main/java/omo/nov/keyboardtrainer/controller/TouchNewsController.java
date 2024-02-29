package omo.nov.keyboardtrainer.controller;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.NewsSeenTouch;
import omo.nov.keyboardtrainer.entity.enums.SeenTouch;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.service.TouchService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seenTouch")
@RequiredArgsConstructor
public class TouchNewsController {
    private final TouchService touchService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestParam SeenTouch seenTouch, @RequestParam String deviceIp, List<Long> newsId) {
        return touchService.add(seenTouch, deviceIp, newsId);
    }

    @GetMapping("/get")
    public Page<NewsSeenTouch> get(@RequestParam int page, @RequestParam int size) {
        return touchService.get(page, size);
    }

}
