package omo.nov.keyboardtrainer.mapper;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.PrizeContest;
import omo.nov.keyboardtrainer.payload.PrizeContestDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrizeMapper {
    public PrizeContestDTO toDTO(PrizeContest prizeContest) {
        return PrizeContestDTO.builder()
                .id(prizeContest.getId())
                .description(prizeContest.getDescription())
                .title(prizeContest.getTitle())
                .imgUrl(prizeContest.getImageUrl())
                .build();
    }
}
