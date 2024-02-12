package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.PrizeContest;
import omo.nov.keyboardtrainer.exception.MainException;
import omo.nov.keyboardtrainer.exception.NotFoundException;
import omo.nov.keyboardtrainer.mapper.PrizeMapper;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.PrizeContestDTO;
import omo.nov.keyboardtrainer.repository.ContestRepository;
import omo.nov.keyboardtrainer.repository.PrizeContestRepository;
import omo.nov.keyboardtrainer.service.PrizeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrizeServiceImpl implements PrizeService {
    private final ContestRepository contestRepository;
    private final PrizeContestRepository prizeContestRepository;
    private final PrizeMapper prizeMapper;
    private static final String MAIN_UPLOAD_DIRECTORY = "documents";
    @Override
    public ResponseEntity<ApiResponse> add(Long contestId, String description, String title, MultipartFile multipartFile) {
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            Path uploadDirectory = Paths.get(MAIN_UPLOAD_DIRECTORY);
            if (!Files.exists(uploadDirectory)) Files.createDirectories(uploadDirectory);
            Path filePath = uploadDirectory.resolve(fileName);
            multipartFile.transferTo(filePath);
            prizeContestRepository.save(PrizeContest.builder().contest(contestRepository.findById(contestId).orElseThrow()).description(description).title(title).imageUrl("/api/v1/file/getFile?path=" + MAIN_UPLOAD_DIRECTORY + "/" + fileName).build());
            return ResponseEntity.ok(ApiResponse.builder().status(200).message("Ok").build());
        } catch (Exception e) {
            throw new MainException("Hato request");
        }
    }

    @Override
    public List<PrizeContestDTO> getAllByContest(Long contestId) {
        return prizeContestRepository.findAllByContestId(contestId).stream().map(prizeMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PrizeContestDTO getById(Long id) {
        return prizeContestRepository.findById(id).map(prizeMapper::toDTO).orElseThrow(() -> new NotFoundException("Not found"));
    }

    @Override
    public ResponseEntity<ApiResponse> delete(Long id) {
        try {
            prizeContestRepository.deleteById(id);
            return ResponseEntity.ok(ApiResponse.builder().message("Deleted").status(200).build());
        } catch (Exception e) {
            throw new MainException("Exception");
        }
    }
}
