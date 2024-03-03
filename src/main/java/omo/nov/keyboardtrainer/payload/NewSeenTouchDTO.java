package omo.nov.keyboardtrainer.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omo.nov.keyboardtrainer.entity.News;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewSeenTouchDTO {
    private News news;
    private Long seenCount;

    private Long touchCount;
}
