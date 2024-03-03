package omo.nov.keyboardtrainer.repository;

import omo.nov.keyboardtrainer.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM news order by created_at DESC  LIMIT  10")
    List<News> orderByCreated();
}
