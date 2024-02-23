package omo.nov.keyboardtrainer.config;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.Contest;
import omo.nov.keyboardtrainer.entity.News;
import omo.nov.keyboardtrainer.entity.User;
import omo.nov.keyboardtrainer.entity.enums.Region;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.entity.enums.SystemRoleName;
import omo.nov.keyboardtrainer.repository.ContestRepository;
import omo.nov.keyboardtrainer.repository.NewsRepository;
import omo.nov.keyboardtrainer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DataLoaderConfig implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String sqlInitMode;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ContestRepository contestRepository;
    private final NewsRepository newsRepository;

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(sqlInitMode, "always")) {
            userRepository.save(User.builder().name("ADMIN").surname("ADMINOV").region(Region.Sirdaryo).status(true).passwords(passwordEncoder.encode("admin")).systemRoleName(SystemRoleName.ROLE_ADMIN).deviceIp("NO IP").phoneNumber("+998950960153").build());
            contestRepository.save(Contest.builder().title("TestContest").startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 3, 1, 12, 20, 0, 0)).description("DescriptionTest").limitSecondContest(60).status(Status.BOSHLANMAGAN).build());
            for (int i = 0; i < 15; i++) {
                newsRepository.save(News.builder().imageUrl("https://i.ytimg.com/vi/HH6lm370kH0/hqdefault.jpg?sqp=-oaymwEjCNACELwBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLAG8xLAw1tA_LGx5AbmUuUn9TquOg").link("https://youtu.be/V1PyfsaPnLo").build());
            }
            System.out.println("Contest news va admin qo'shildi");
        }
    }
}