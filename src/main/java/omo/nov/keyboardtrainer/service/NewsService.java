package omo.nov.keyboardtrainer.service;

import omo.nov.keyboardtrainer.entity.News;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    ResponseEntity<ApiResponse> add(String link, MultipartFile multipartFile);

    List<News> getLastNews();


    ResponseEntity<ApiResponse> delete(Long newsId);
}
