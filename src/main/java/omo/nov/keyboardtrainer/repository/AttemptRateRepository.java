package omo.nov.keyboardtrainer.repository;

import omo.nov.keyboardtrainer.entity.AttemptRate;
import omo.nov.keyboardtrainer.payload.AttemptRateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttemptRateRepository extends JpaRepository<AttemptRate, UUID> {

    Optional<AttemptRate> findByUserIdAndContestId(Long id, Long contestId);

    @Query(value = "SELECT new omo.nov.keyboardtrainer.payload.AttemptRateDTO(ar.user , ar.trueLetterCount , ar.falseLetterCount , ar.commonTrue , ar.startAt , ar.endAt) FROM AttemptRate ar where ar.contest.id=:contest_id order by ar.commonTrue desc")
    Page<AttemptRateDTO> findAllByContestIdOrderByCommonTrue(@Param(value = "contest_id") Long contest_id, Pageable pageable);

    List<AttemptRate> findAllByContestIdOrderByCommonTrueDesc(Long contest_id);

}
