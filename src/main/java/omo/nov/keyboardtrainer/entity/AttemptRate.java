package omo.nov.keyboardtrainer.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "contest_id"})})
public class AttemptRate extends AbsUUIDEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Contest contest;
    private Integer trueLetterCount;
    private Integer falseLetterCount;
    private Integer commonTrue;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp startAt;
    @JsonDeserialize(using = TimestampDeserializer.class)
    private Timestamp endAt;
}
