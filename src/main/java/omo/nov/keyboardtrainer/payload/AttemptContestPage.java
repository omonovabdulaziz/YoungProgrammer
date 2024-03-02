package omo.nov.keyboardtrainer.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omo.nov.keyboardtrainer.util.TimestampDeserializer;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptContestPage {
    private UUID id;
    private Integer trueLetterCount;
    private Integer falseLetterCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp endAt;
}
