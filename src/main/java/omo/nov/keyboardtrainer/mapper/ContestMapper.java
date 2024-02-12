package omo.nov.keyboardtrainer.mapper;

import com.sun.tools.jconsole.JConsoleContext;
import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.Contest;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.payload.ContestDTO;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContestMapper {
    public Contest toEntity(ContestDTO contestDTO) {
        return Contest.builder().startAt(contestDTO.getStartAt()).endAt(contestDTO.getEndAt()).title(contestDTO.getTitle()).limitSecondContest(contestDTO.getLimitSecondContest()).description(contestDTO.getDescription()).status(Status.BOSHLANMAGAN).build();
    }

    public Contest toEntity(ContestDTO contestDTO, Contest contest) {
        contest.setTitle(contestDTO.getTitle());
        contest.setDescription(contestDTO.getDescription());
        contest.setEndAt(contestDTO.getEndAt());
        contest.setStartAt(contestDTO.getStartAt());
        contest.setLimitSecondContest(contestDTO.getLimitSecondContest());
        return contest;
    }

    public ContestDTO toDTO(Contest contest) {
        return ContestDTO.builder()
                .startAt(contest.getStartAt())
                .title(contest.getTitle())
                .description(contest.getDescription())
                .limitSecondContest(contest.getLimitSecondContest())
                .endAt(contest.getEndAt())
                .build();
    }
}
