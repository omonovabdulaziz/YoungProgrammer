package omo.nov.keyboardtrainer.service;

import omo.nov.keyboardtrainer.entity.Regular;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.NewRegularDTO;
import omo.nov.keyboardtrainer.payload.RegularCommon;
import omo.nov.keyboardtrainer.payload.RegularDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface RegularService {
    ResponseEntity<ApiResponse> add(NewRegularDTO regularDTO);

    Page<RegularDTO> getRegularPage(int page, int size);

    Regular getById(UUID id);

    RegularCommon   getRate(Integer limitSecond , int page , int size);

    RegularCommon getRateNotUser(Integer limitSecond, int page, int size);

}
