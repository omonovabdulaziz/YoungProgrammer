package omo.nov.keyboardtrainer.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.entity.temp.AbsLongEntity;
import omo.nov.keyboardtrainer.util.TimestampDeserializer;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contest extends AbsLongEntity {
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    @Column(nullable = false)
    private Timestamp startAt;
    @Column(nullable = false)
    private Timestamp endAt;
    @Column(nullable = false)
    private Integer limitSecondContest;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
    private List<AttemptContest> attemptContests;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
    private List<AttemptRate> attemptRates;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "contest", cascade = CascadeType.ALL)
    private List<PrizeContest> prizeContests;
}
