package omo.nov.keyboardtrainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class KeyboardTrainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeyboardTrainerApplication.class, args);
    }
}
