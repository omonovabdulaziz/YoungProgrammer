package omo.nov.keyboardtrainer.util;

import omo.nov.keyboardtrainer.exception.MainException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class ImageUploader {

    public static void worker(String MAIN_UPLOAD_DIRECTORY, String fileName, MultipartFile multipartFile) {
        try {
            Path uploadDirectory = Paths.get(MAIN_UPLOAD_DIRECTORY);
            if (!Files.exists(uploadDirectory)) Files.createDirectories(uploadDirectory);
            Path filePath = uploadDirectory.resolve(fileName);
            multipartFile.transferTo(filePath);
        } catch (Exception e) {
            throw new MainException("Error");
        }
    }
}
