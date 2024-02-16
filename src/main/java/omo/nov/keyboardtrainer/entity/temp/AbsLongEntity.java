package omo.nov.keyboardtrainer.entity.temp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;
import omo.nov.keyboardtrainer.util.TimestampDeserializer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@MappedSuperclass
@Data
public abstract class AbsLongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    @Column(updatable = false)
    @JsonDeserialize(using = TimestampDeserializer.class)

    private LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonDeserialize(using = TimestampDeserializer.class)

    private LocalDateTime updatedAt;
}
