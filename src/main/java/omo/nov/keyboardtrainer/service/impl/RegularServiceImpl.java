package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.config.SecurityConfiguration;
import omo.nov.keyboardtrainer.entity.Regular;
import omo.nov.keyboardtrainer.entity.RegularRate;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.exception.NotFoundException;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.RegularCommon;
import omo.nov.keyboardtrainer.payload.RegularDTO;
import omo.nov.keyboardtrainer.repository.RegularRateRepository;
import omo.nov.keyboardtrainer.repository.RegularRepository;
import omo.nov.keyboardtrainer.service.RegularService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegularServiceImpl implements RegularService {
    private final RegularRepository regularRepository;
    private final RegularRateRepository regularRateRepository;

    @Override
    public ResponseEntity<ApiResponse> add(RegularDTO regularDTO) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        Optional<RegularRate> optionalRegularRate = regularRateRepository.findByUserIdAndLimitSecondRegulate(systemUser.getId(), regularDTO.getLimitSecondRegular());
        if (optionalRegularRate.isEmpty()) {
            regularRateRepository.save(RegularRate.builder().user(systemUser).startAt(regularDTO.getStartAt()).endAt(regularDTO.getEndAt()).limitSecondRegulate(regularDTO.getLimitSecondRegular()).falseLetterCount(regularDTO.getFalseLetterCount()).trueLetterCount(regularDTO.getTrueLetterCount()).commonTrue(regularDTO.getTrueLetterCount() - regularDTO.getFalseLetterCount()).build());
        } else {
            RegularRate regularRate = optionalRegularRate.get();
            int i = regularRate.getTrueLetterCount() - regularRate.getFalseLetterCount();
            int i1 = regularDTO.getTrueLetterCount() - regularDTO.getFalseLetterCount();
            if (i1 > i) {
                regularRate.setTrueLetterCount(regularDTO.getTrueLetterCount());
                regularRate.setFalseLetterCount(regularDTO.getFalseLetterCount());
                regularRate.setCommonTrue(i1);
                regularRate.setStartAt(regularDTO.getStartAt());
                regularRate.setEndAt(regularDTO.getEndAt());
                regularRateRepository.save(regularRate);
            }
        }
        regularRepository.save(Regular.builder().startAt(regularDTO.getStartAt()).commonTrue(regularDTO.getTrueLetterCount() - regularDTO.getFalseLetterCount()).endAt(regularDTO.getEndAt()).user(systemUser).falseLetterCount(regularDTO.getFalseLetterCount()).trueLetterCount(regularDTO.getTrueLetterCount()).limitSecondRegular(regularDTO.getLimitSecondRegular()).build());
        return ResponseEntity.ok(ApiResponse.builder().message("Ok").status(200).build());
    }

    @Override
    public Page<RegularDTO> getRegularPage(int page, int size) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        return systemUser.getStatus() ? regularRepository.selectByUser(systemUser.getId(), PageRequest.of(page, size)) : null;
    }

    @Override
    public Regular getById(UUID id) {
        return regularRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
    }

    @Override
    public RegularCommon getRate(Integer limitSecond, int page, int size) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        Long myPlace = 1L;
        if (systemUser.getStatus()) {
            for (Regular regular : regularRepository.findAllByLimitSecondRegularOrderByCommonTrueDesc(limitSecond)) {
                if (regular.getUser().equals(systemUser))
                    break;
                myPlace++;
            }
        } else {
            myPlace = null;
        }
        return RegularCommon.builder().myPlace(myPlace).regularDTOPage(regularRateRepository.getRegularDTOPage(PageRequest.of(page, size))).build();
    }

}
