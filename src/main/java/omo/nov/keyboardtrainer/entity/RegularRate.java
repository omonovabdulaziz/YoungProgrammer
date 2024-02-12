package omo.nov.keyboardtrainer.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;
import omo.nov.keyboardtrainer.entity.temp.AbsUUIDEntity;
import omo.nov.keyboardtrainer.util.TimestampDeserializer;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "limit_second_regulate"})})
public class RegularRate extends AbsUUIDEntity {
    @ManyToOne
    private User user;
    private Integer trueLetterCount;
    private Integer falseLetterCount;
    private Integer limitSecondRegulate;
    private Integer commonTrue;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp startAt;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp endAt;
}
