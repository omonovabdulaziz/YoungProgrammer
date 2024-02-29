package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.News;
import omo.nov.keyboardtrainer.entity.NewsSeenTouch;
import omo.nov.keyboardtrainer.entity.enums.SeenTouch;
import omo.nov.keyboardtrainer.exception.NotFoundException;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.repository.NewsRepository;
import omo.nov.keyboardtrainer.repository.TouchRepository;
import omo.nov.keyboardtrainer.service.TouchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class NewsSeenTouchServiceImpl implements TouchService {
    private final TouchRepository touchRepository;
    private final NewsRepository newsRepository;

    @Override
    public ResponseEntity<ApiResponse> add(SeenTouch seenTouch, String deviceIp, Long newsId) {
        News news = newsRepository.findById(newsId).orElseThrow(() -> new NotFoundException("NewsTopilmadi"));
        if (!touchRepository.existsByNewsAndDeviceIpAndSeenTouch(news, deviceIp, seenTouch)) {
            touchRepository.save(NewsSeenTouch.builder().news(news).deviceIp(deviceIp).seenTouch(seenTouch).build());
        }
        return ResponseEntity.ok(ApiResponse.builder().message("Ok").status(200).build());
    }

    @Override
    public Page<NewsSeenTouch> get(int page, int size) {
        return touchRepository.findAll(PageRequest.of(page, size));
    }
}
