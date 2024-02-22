package omo.nov.keyboardtrainer.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.util.TimestampDeserializer;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegularDTO {
    private UUID id;
    private User user;
    private Integer limitSecondRegular;
    private Integer trueLetterCount;
    private Integer falseLetterCount;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp startAt;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp endAt;
}
