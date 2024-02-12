package omo.nov.keyboardtrainer.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omo.nov.keyboardtrainer.entity.enums.Region;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDTO {
    private String name;
    private String surname;
    private String phoneNumber;
    private String passwords;
    private Region region;
    private Integer age;
}
