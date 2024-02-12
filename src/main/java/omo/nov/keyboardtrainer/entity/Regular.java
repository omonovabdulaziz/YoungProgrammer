package omo.nov.keyboardtrainer.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import omo.nov.keyboardtrainer.entity.temp.AbsLongEntity;
import omo.nov.keyboardtrainer.entity.temp.AbsUUIDEntity;
import omo.nov.keyboardtrainer.util.TimestampDeserializer;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Regular extends AbsUUIDEntity {
    @ManyToOne
    private User user;
    private Integer limitSecondRegular;
    private Integer trueLetterCount;
    private Integer falseLetterCount;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp startAt;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp endAt;
    private Integer commonTrue;
}
