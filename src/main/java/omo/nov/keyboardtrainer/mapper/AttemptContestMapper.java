package omo.nov.keyboardtrainer.mapper;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.AttemptContest;
import omo.nov.keyboardtrainer.payload.AttemptContestId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttemptContestMapper {
    private final ContestMapper contestMapper;

    public AttemptContestId toDTO(AttemptContest attemptContest) {
        return AttemptContestId.builder().user(attemptContest.getUser()).trueLetterCount(attemptContest.getTrueLetterCount()).falseLetterCount(attemptContest.getFalseLetterCount()).contestDTO(contestMapper.toDTO(attemptContest.getContest())).startAt(attemptContest.getStartAt()).endAt(attemptContest.getEndAt()).build();
    }
}
