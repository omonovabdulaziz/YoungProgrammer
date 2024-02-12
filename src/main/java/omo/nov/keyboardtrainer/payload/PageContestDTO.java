package omo.nov.keyboardtrainer.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.util.TimestampDeserializer;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageContestDTO {
    private Long id;
    private String title;
    private String description;
    @JsonDeserialize(using = TimestampDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp startAt;
    @JsonDeserialize(using = TimestampDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp endAt;
    private Integer limitSecondContest;
    private Status status;
}