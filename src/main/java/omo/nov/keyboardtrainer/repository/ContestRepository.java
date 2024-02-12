package omo.nov.keyboardtrainer.repository;

import omo.nov.keyboardtrainer.entity.Contest;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.payload.PageContestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContestRepository extends JpaRepository<Contest, Long> {


    @Query("SELECT new omo.nov.keyboardtrainer.payload.PageContestDTO(c.id ,c.title, c.description, c.startAt, c.endAt, c.limitSecondContest , c.status) FROM Contest c")
    Page<PageContestDTO> findAllContests(PageRequest pageRequest);

    @Query("SELECT new omo.nov.keyboardtrainer.payload.PageContestDTO(c.id , c.title, c.description, c.startAt, c.endAt, c.limitSecondContest , c.status) FROM Contest c WHERE c.status = :status")
    Page<PageContestDTO> findAllContestsByValidity(@Param("status") Status status, PageRequest pageRequest);

    @Query("SELECT new omo.nov.keyboardtrainer.payload.PageContestDTO(c.id , c.title, c.description, c.startAt, c.endAt, c.limitSecondContest , c.status) FROM Contest c where c.id=:id")
    PageContestDTO findByCustom(@Param(value = "id") Long id);

    //OZGARADI
    @Query("SELECT  new omo.nov.keyboardtrainer.payload.PageContestDTO(c.id ,c.title , c.description , c.startAt ,c.endAt , c.limitSecondContest , c.status) FROM  Contest c order by c.createdAt limit 1")
    PageContestDTO getLastContest();
}
