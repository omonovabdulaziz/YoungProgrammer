package omo.nov.keyboardtrainer.service.impl;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.config.SecurityConfiguration;
import omo.nov.keyboardtrainer.entity.Regular;
import omo.nov.keyboardtrainer.entity.RegularRate;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.exception.ForbiddenException;
import omo.nov.keyboardtrainer.exception.NotFoundException;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.NewRegularDTO;
import omo.nov.keyboardtrainer.payload.RegularCommon;
import omo.nov.keyboardtrainer.payload.RegularDTO;
import omo.nov.keyboardtrainer.repository.RegularRateRepository;
import omo.nov.keyboardtrainer.repository.RegularRepository;
import omo.nov.keyboardtrainer.service.RegularService;
import omo.nov.keyboardtrainer.util.Encrypter;
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
    public ResponseEntity<ApiResponse> add(NewRegularDTO newRegularDTO) throws Exception {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        Optional<RegularRate> optionalRegularRate = regularRateRepository.findByUserIdAndLimitSecondRegulate(systemUser.getId(), newRegularDTO.getLimitSecondRegular());
        Integer falseLetterCount = Encrypter.decrypt(newRegularDTO.getFalseLetterCount());
        Integer trueLetterCount = Encrypter.decrypt(newRegularDTO.getTrueLetterCount());
        if (falseLetterCount == null || trueLetterCount == null)
            throw new ForbiddenException("Forbidden");

        if (optionalRegularRate.isEmpty()) {
            regularRateRepository.save(RegularRate.builder().user(systemUser).startAt(newRegularDTO.getStartAt()).endAt(newRegularDTO.getEndAt()).limitSecondRegulate(newRegularDTO.getLimitSecondRegular()).falseLetterCount(falseLetterCount).trueLetterCount(trueLetterCount).commonTrue(trueLetterCount - falseLetterCount).build());
        } else {
            RegularRate regularRate = optionalRegularRate.get();
            int i = regularRate.getTrueLetterCount() - regularRate.getFalseLetterCount();
            int i1 = trueLetterCount - falseLetterCount;
            if (i1 > i) {
                regularRate.setTrueLetterCount(trueLetterCount);
                regularRate.setFalseLetterCount(falseLetterCount);
                regularRate.setCommonTrue(i1);
                regularRate.setStartAt(newRegularDTO.getStartAt());
                regularRate.setEndAt(newRegularDTO.getEndAt());
                regularRateRepository.save(regularRate);
            }
        }
        regularRepository.save(Regular.builder().startAt(newRegularDTO.getStartAt()).endAt(newRegularDTO.getEndAt()).commonTrue(trueLetterCount - falseLetterCount).falseLetterCount(falseLetterCount).trueLetterCount(trueLetterCount).limitSecondRegular(newRegularDTO.getLimitSecondRegular()).user(systemUser).build());
        return ResponseEntity.ok(ApiResponse.builder().message("Ok").status(200).build());
    }

    @Override
    public Page<RegularDTO> getRegularPage(int page, int size) {
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        return regularRepository.selectByUser(systemUser.getId(), PageRequest.of(page, size));
    }

    @Override
    public Regular getById(UUID id) {
        return regularRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found"));
    }

    @Override
    public RegularCommon getRate(Integer limitSecond, int page, int size) {
        Page<RegularDTO> regularDTOPage = regularRateRepository.getRegularDTOPage(PageRequest.of(page, size));
        User systemUser = SecurityConfiguration.getOwnSecurityInformation();
        Long myPlace = 1L;
        if (systemUser.getStatus()) {
            for (RegularRate regularRate : regularRateRepository.findAllByLimitSecondRegulateAndUser_StatusAndFalseLetterCountLessThanOrderByCommonTrueDesc(limitSecond, true, 13)) {
                if (regularRate.getUser().equals(systemUser))
                    break;
                myPlace++;
            }
        } else {
            myPlace = null;
        }
        return RegularCommon.builder().myPlace(myPlace).regularDTOPage(regularDTOPage).build();
    }

    @Override
    public RegularCommon getRateNotUser(Integer limitSecond, int page, int size) {
        Page<RegularDTO> regularDTOPage = regularRateRepository.getRegularDTOPage(PageRequest.of(page, size));
        return RegularCommon.builder().myPlace(null).regularDTOPage(regularDTOPage).build();
    }

}
