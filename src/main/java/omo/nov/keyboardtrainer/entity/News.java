package omo.nov.keyboardtrainer.entity;

import jakarta.persistence.Entity;
import lombok.*;
import omo.nov.keyboardtrainer.entity.temp.AbsLongEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class News extends AbsLongEntity {
    private String imageUrl;
    private String link;
}
