package omo.nov.keyboardtrainer.payload;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
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
public class ContestDTO {
    @NotNull(message = "Insert title")
    private String title;
    private String description;
    @NotNull(message = "Insert title")
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp startAt;
    @NotNull(message = "Insert title")
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp endAt;
    @NotNull(message = "Insert title")
    private Integer limitSecondContest;
}