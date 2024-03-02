package omo.nov.keyboardtrainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@SpringBootApplication
public class KeyboardTrainerApplication {
    public static void main(String[] args) {
        SpringApplication.run(KeyboardTrainerApplication.class, args);
    }
}
