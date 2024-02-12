package omo.nov.keyboardtrainer.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegularCommon {
    private Long myPlace;
    private Page<RegularDTO> regularDTOPage;
}
