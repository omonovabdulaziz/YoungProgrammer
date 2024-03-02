package omo.nov.keyboardtrainer.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omo.nov.keyboardtrainer.util.TimestampDeserializer;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewRegularDTO {
    private Integer limitSecondRegular;
    private String trueLetterCount;
    private String falseLetterCount;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp startAt;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp endAt;
}
