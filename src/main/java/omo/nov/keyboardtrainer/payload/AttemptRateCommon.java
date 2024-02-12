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
public class AttemptRateCommon {
    private Long myPlace;
    Page<AttemptRateDTO> attemptRateDTOS;
}
