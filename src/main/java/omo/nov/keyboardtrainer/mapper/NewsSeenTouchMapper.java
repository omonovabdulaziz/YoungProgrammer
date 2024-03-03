package omo.nov.keyboardtrainer.mapper;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.NewsSeenTouch;
import omo.nov.keyboardtrainer.entity.enums.SeenTouch;
import omo.nov.keyboardtrainer.payload.NewSeenTouchDTO;
import omo.nov.keyboardtrainer.repository.TouchRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsSeenTouchMapper {
    private final TouchRepository touchRepository;

    public NewSeenTouchDTO toDTO(NewsSeenTouch newsSeenTouch) {
        return NewSeenTouchDTO.builder()
                .news(newsSeenTouch.getNews())
                .seenCount((long) touchRepository.findAllBySeenTouchAndNews_Id(SeenTouch.SEEN, newsSeenTouch.getNews().getId()).size())
                .touchCount((long) touchRepository.findAllBySeenTouchAndNews_Id(SeenTouch.TOUCH, newsSeenTouch.getNews().getId()).size())
                .build();
    }
}
