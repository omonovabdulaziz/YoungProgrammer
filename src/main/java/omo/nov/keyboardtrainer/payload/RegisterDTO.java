package omo.nov.keyboardtrainer.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import omo.nov.keyboardtrainer.entity.enums.Region;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDTO {
    @NotNull(message = "Insert name")
    private String name;
    @NotNull(message = "Insert surname")
    private String surname;
    @NotNull(message = "Insert phoneNumber")
    private String phoneNumber;
    @NotNull(message = "Insert password")
    private String password;
    private String deviceIp;
    private Region region;
    private Integer age;
}
