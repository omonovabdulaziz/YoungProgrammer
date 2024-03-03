package omo.nov.keyboardtrainer.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.News;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.service.NewsService;
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
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestParam String link, @RequestParam("file") @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "File to upload", required = true, content = @Content(
            mediaType = "multipart/form-data", schema = @Schema(type = "string", format = "binary")
    )) MultipartFile multipartFile) {
        return newsService.add(link, multipartFile);
    }

    @GetMapping("/getLastNews")
    public List<News> getLastNews() {
        return newsService.getLastNews();
    }

    @DeleteMapping("/delete/{newsId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long newsId) {
        return newsService.delete(newsId);
    }
}
