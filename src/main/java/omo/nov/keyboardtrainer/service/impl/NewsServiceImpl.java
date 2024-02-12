package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.News;
import omo.nov.keyboardtrainer.exception.MainException;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.repository.NewsRepository;
import omo.nov.keyboardtrainer.service.NewsService;
import omo.nov.keyboardtrainer.util.ImageUploader;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    private static final String MAIN_UPLOAD_DIRECTORY = "documents";


    @Override
    public ResponseEntity<ApiResponse> add(String link, MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        ImageUploader.worker(MAIN_UPLOAD_DIRECTORY, fileName, multipartFile);
        newsRepository.save(News.builder().link(link).imageUrl("/api/v1/file/getFile?path=" + MAIN_UPLOAD_DIRECTORY + "/" + fileName).build());
        return ResponseEntity.ok(ApiResponse.builder().status(200).message("Ok").build());
    }

    @Override
    public List<News> getLastNews() {
        return newsRepository.orderByCreated();
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long newsId) {
        try {
            newsRepository.deleteById(newsId);
            return ResponseEntity.ok(ApiResponse.builder().message("Deleted").status(200).build());
        } catch (Exception e) {
            throw new MainException("Error");
        }
    }


}
