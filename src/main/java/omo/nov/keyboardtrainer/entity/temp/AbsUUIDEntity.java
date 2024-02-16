package omo.nov.keyboardtrainer.entity.temp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import omo.nov.keyboardtrainer.util.TimestampDeserializer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@MappedSuperclass
public abstract class AbsUUIDEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    @CreationTimestamp
    @Column(updatable = false)
    @JsonDeserialize(using = TimestampDeserializer.class)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @JsonDeserialize(using = TimestampDeserializer.class)
    private LocalDateTime updatedAt;
}