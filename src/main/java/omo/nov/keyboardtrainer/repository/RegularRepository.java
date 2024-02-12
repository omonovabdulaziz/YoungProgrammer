package omo.nov.keyboardtrainer.repository;

import omo.nov.keyboardtrainer.entity.Regular;
import omo.nov.keyboardtrainer.payload.RegularDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RegularRepository extends JpaRepository<Regular, UUID> {
    @Query(value = "SELECT new omo.nov.keyboardtrainer.payload.RegularDTO(r.id , r.limitSecondRegular , r.falseLetterCount , r.falseLetterCount , r.startAt , r.endAt) FROM Regular  r where r.user.id=:userId order by r.createdAt desc")
    Page<RegularDTO> selectByUser(@Param(value = "userId") Long userId , PageRequest pageRequest);
    List<Regular> findAllByLimitSecondRegularOrderByCommonTrueDesc(Integer limitSecondRegular);
}
