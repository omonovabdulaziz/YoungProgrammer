package omo.nov.keyboardtrainer.config;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.entity.enums.Region;
import omo.nov.keyboardtrainer.entity.enums.SystemRoleName;
import omo.nov.keyboardtrainer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String sqlInitMode;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(sqlInitMode, "always")) {
            userRepository.save(User.builder().name("ADMIN").surname("ADMINOV").region(Region.Sirdaryo).status(true).passwords(passwordEncoder.encode("admin")).systemRoleName(SystemRoleName.ROLE_ADMIN).deviceIp("NO IP").phoneNumber("+998950960153").build());
            System.out.println("Admin qo'shildi");
        }
    }
}