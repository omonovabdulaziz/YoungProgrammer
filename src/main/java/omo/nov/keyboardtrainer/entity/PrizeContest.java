package omo.nov.keyboardtrainer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import omo.nov.keyboardtrainer.entity.temp.AbsLongEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PrizeContest extends AbsLongEntity {
    @ManyToOne
    private Contest contest;
    @Column(columnDefinition = "text")
    private String description;
    @Column(nullable = false)
    private String title;
    private String imageUrl;
}
