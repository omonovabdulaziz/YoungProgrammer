package omo.nov.keyboardtrainer.repository;

import omo.nov.keyboardtrainer.entity.RegularRate;
import omo.nov.keyboardtrainer.payload.RegularDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RegularRateRepository extends JpaRepository<RegularRate, UUID> {
    Optional<RegularRate> findByUserIdAndLimitSecondRegulate(Long user_id, Integer limitSecondRegulate);


    @Query(value = "SELECT new omo.nov.keyboardtrainer.payload.RegularDTO(rg.id , rg.user ,rg.limitSecondRegulate , rg.trueLetterCount , rg.falseLetterCount , rg.startAt , rg.endAt) FROM RegularRate rg WHERE  rg.user.status=true order by rg.commonTrue desc")
    Page<RegularDTO> getRegularDTOPage(PageRequest pageRequest);


    List<RegularRate> findAllByLimitSecondRegulateAndUser_StatusOrderByCommonTrueDesc(Integer limitSecondRegulate, Boolean user_status);


}
