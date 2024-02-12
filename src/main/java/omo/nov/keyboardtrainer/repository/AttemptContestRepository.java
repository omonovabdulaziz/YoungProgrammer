package omo.nov.keyboardtrainer.repository;

import omo.nov.keyboardtrainer.entity.AttemptContest;
import omo.nov.keyboardtrainer.payload.AttemptContestPage;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.UUID;

public interface AttemptContestRepository extends JpaRepository<AttemptContest, UUID> {
    @Query(value = "SELECT new omo.nov.keyboardtrainer.payload.AttemptContestPage(ac.id, ac.trueLetterCount, ac.falseLetterCount , ac.startAt , ac.endAt)  FROM AttemptContest ac WHERE ac.user.id =:userId AND ac.contest.id =:contestId")
    Page<AttemptContestPage> getAllAttemptByUserAndContest(@Param(value = "userId") Long userId, @Param(value = "contestId") Long contestId, PageRequest pageRequest);


}
