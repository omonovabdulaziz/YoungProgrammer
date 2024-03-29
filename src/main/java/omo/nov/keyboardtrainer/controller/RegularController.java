package omo.nov.keyboardtrainer.controller;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.Regular;
import omo.nov.keyboardtrainer.payload.ApiResponse;
import omo.nov.keyboardtrainer.payload.NewRegularDTO;
import omo.nov.keyboardtrainer.payload.RegularCommon;
import omo.nov.keyboardtrainer.payload.RegularDTO;
import omo.nov.keyboardtrainer.service.RegularService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/regular")
@RequiredArgsConstructor
public class RegularController {
    private final RegularService regularService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> add(@RequestBody NewRegularDTO newRegularDTO) throws Exception {
        return regularService.add(newRegularDTO);
    }

    @GetMapping("/getRegularByPage")
    public Page<RegularDTO> getRegularPage(@RequestParam int page, @RequestParam int size) {
        return regularService.getRegularPage(page, size);
    }

    @GetMapping("/getById")
    public Regular getById(@RequestParam UUID id) {
        return regularService.getById(id);
    }

    @GetMapping("/getRate")
    public RegularCommon getRate(@RequestParam Integer limitSecond, @RequestParam int page, @RequestParam int size) {
        return regularService.getRate(limitSecond, page, size);
    }

    @GetMapping("/getRateNotUser")
    public RegularCommon getRateNotUser(@RequestParam Integer limitSecond, @RequestParam int page, @RequestParam int size) {
        return regularService.getRateNotUser(limitSecond, page, size);
    }

    @GetMapping("/getRegularByUserId/{userId}")
    public Page<RegularDTO> getByUserId(@PathVariable Long userId, @RequestParam int page, @RequestParam int size) {
        return regularService.getRegularByUserId(userId, page, size);
    }
}
