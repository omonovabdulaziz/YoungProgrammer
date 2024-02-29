package omo.nov.keyboardtrainer.entity;

import jakarta.persistence.*;
import lombok.*;
import omo.nov.keyboardtrainer.entity.enums.SeenTouch;
import omo.nov.keyboardtrainer.entity.temp.AbsLongEntity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsSeenTouch extends AbsLongEntity {
    @ManyToOne
    private News news;
    private String deviceIp;
    @Enumerated(EnumType.STRING)
    private SeenTouch seenTouch;
}
