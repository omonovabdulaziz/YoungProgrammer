package omo.nov.keyboardtrainer.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omo.nov.keyboardtrainer.entity.User;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttemptRateDTO {
    private User user;
    private Integer trueLetterCount;
    private Integer falseLetterCount;
    private Integer commonTrue;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp startAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Timestamp endAt;

}
