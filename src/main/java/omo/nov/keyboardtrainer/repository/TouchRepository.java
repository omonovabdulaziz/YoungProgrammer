package omo.nov.keyboardtrainer.repository;

import omo.nov.keyboardtrainer.entity.News;
import omo.nov.keyboardtrainer.entity.NewsSeenTouch;
import omo.nov.keyboardtrainer.entity.enums.SeenTouch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TouchRepository extends JpaRepository<NewsSeenTouch, Long> {
    Boolean existsByNewsAndDeviceIpAndSeenTouch(News news, String deviceIp, SeenTouch seenTouch);

}
