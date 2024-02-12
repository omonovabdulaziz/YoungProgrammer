package omo.nov.keyboardtrainer.repository;

import omo.nov.keyboardtrainer.entity.PrizeContest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrizeContestRepository extends JpaRepository<PrizeContest, Long> {
    List<PrizeContest> findAllByContestId(Long contest_id);
}
