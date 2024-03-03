package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.News;
import omo.nov.keyboardtrainer.entity.NewsSeenTouch;
import omo.nov.keyboardtrainer.entity.enums.SeenTouch;
import omo.nov.keyboardtrainer.mapper.NewsSeenTouchMapper;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.NewSeenTouchDTO;
import omo.nov.keyboardtrainer.repository.NewsRepository;
import omo.nov.keyboardtrainer.repository.TouchRepository;
import omo.nov.keyboardtrainer.service.TouchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NewsSeenTouchServiceImpl implements TouchService {
    private final TouchRepository touchRepository;
    private final NewsRepository newsRepository;
    private final NewsSeenTouchMapper newsSeenTouchMapper;

    @Override
    public ResponseEntity<ApiResponse> add(SeenTouch seenTouch, String deviceIp, List<Long> newsId) {
        for (Long l : newsId) {
            News news = newsRepository.findById(l).orElse(null);
            if (news != null && !touchRepository.existsByNewsAndDeviceIpAndSeenTouch(news, deviceIp, seenTouch)) {
                touchRepository.save(NewsSeenTouch.builder().news(news).deviceIp(deviceIp).seenTouch(seenTouch).build());
            }
        }
        return ResponseEntity.ok(ApiResponse.builder().message("Ok").status(200).build());
    }


    @Override
    public List<NewSeenTouchDTO> get() {
        List<NewSeenTouchDTO> newSeenTouchDTOS = new ArrayList<>();
        for (NewsSeenTouch newsSeenTouch : touchRepository.findAll()) {
            newSeenTouchDTOS.add(newsSeenTouchMapper.toDTO(newsSeenTouch));
        }
        return newSeenTouchDTOS;
    }
}
