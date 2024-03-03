package omo.nov.keyboardtrainer.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.News;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.service.NewsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> add(@RequestParam String link, @RequestParam MultipartFile multipartFile) {
        return newsService.add(link, multipartFile);
    }

    @GetMapping("/getLastNews")
    public List<News> getLastNews() {
        return newsService.getLastNews();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long newsId) {
        return newsService.delete(newsId);
    }
}
