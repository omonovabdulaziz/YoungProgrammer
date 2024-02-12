package omo.nov.keyboardtrainer.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrizeContestDTO {
    private Long id;
    private String title;
    private String description;
    private String imgUrl;
}
