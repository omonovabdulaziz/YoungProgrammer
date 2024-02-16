package omo.nov.keyboardtrainer.config;

import lombok.RequiredArgsConstructor;
import omo.nov.keyboardtrainer.entity.*;
import omo.nov.keyboardtrainer.entity.enums.Region;
import omo.nov.keyboardtrainer.entity.enums.Status;
import omo.nov.keyboardtrainer.entity.enums.SystemRoleName;
import omo.nov.keyboardtrainer.repository.*;
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
    private final AttemptContestRepository attemptContestRepository;
    private final AttemptRateRepository attemptRateRepository;
    private final RegularRepository regularRepository;
    private final RegularRateRepository regularRateRepository;
    private final NewsRepository newsRepository;

    @Override
    public void run(String... args) throws Exception {
        if (Objects.equals(sqlInitMode, "always")) {
            userRepository.save(User.builder().name("ADMIN").surname("ADMINOV").region(Region.Sirdaryo).status(true).passwords(passwordEncoder.encode("admin")).systemRoleName(SystemRoleName.ROLE_ADMIN).deviceIp("NO IP").phoneNumber("+998950960153").build());
            //// test uchun
            User user = userRepository.save(User.builder().name("Aziz").surname("Azizov").region(Region.Sirdaryo).status(true).passwords(passwordEncoder.encode("omonov2006")).systemRoleName(SystemRoleName.ROLE_USER).phoneNumber("+998950960154").deviceIp("192.154.32").build());
            Contest contest = contestRepository.save(Contest.builder().title("TestContest").startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 3, 1, 12, 20, 0, 0)).description("DescriptionTest").limitSecondContest(60).status(Status.BOSHLANMAGAN).build());
            for (int i = 0; i < 15; i++) {
                attemptContestRepository.save(AttemptContest.builder().contest(contest).startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 2, 25, 13, 10, 0, 0)).user(user).falseLetterCount(i).trueLetterCount(100).build());
                regularRateRepository.save(RegularRate.builder().user(user).startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 2, 25, 13, 10, 0, 0)).commonTrue(i).trueLetterCount(i).falseLetterCount(0).limitSecondRegulate(i).build());
                regularRepository.save(Regular.builder().startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 2, 25, 13, 10, 0, 0)).commonTrue(i).trueLetterCount(i).falseLetterCount(0).limitSecondRegular(60).user(user).build());
                newsRepository.save(News.builder().imageUrl("https://i.ytimg.com/vi/HH6lm370kH0/hqdefault.jpg?sqp=-oaymwEjCNACELwBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLAG8xLAw1tA_LGx5AbmUuUn9TquOg").link("https://youtu.be/V1PyfsaPnLo").build());
            }
            attemptRateRepository.save(AttemptRate.builder().falseLetterCount(10).trueLetterCount(100).user(user).commonTrue(90).startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 2, 25, 13, 10, 0, 0)).build());
            for (int i = 0; i < 15; i++) {
                User user1 = userRepository.save(User.builder().name("User" + i).surname("sur").age(10).phoneNumber("+9989509" + i).status(true).passwords(passwordEncoder.encode("omonov2006")).deviceIp("No").region(Region.Toshkent).build());
                attemptContestRepository.save(AttemptContest.builder().contest(contest).startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 2, 25, 13, 10, 0, 0)).user(user1).falseLetterCount(i).trueLetterCount(100).build());
                attemptRateRepository.save(AttemptRate.builder().falseLetterCount(10).trueLetterCount(100).user(user1).commonTrue(90).startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 2, 25, 13, 10, 0, 0)).build());
                regularRateRepository.save(RegularRate.builder().user(user1).startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 2, 25, 13, 10, 0, 0)).commonTrue(i).trueLetterCount(i).falseLetterCount(0).limitSecondRegulate(i).build());
                regularRepository.save(Regular.builder().startAt(new Timestamp(2024 - 1900, 2, 25, 12, 20, 0, 0)).endAt(new Timestamp(2024 - 1900, 2, 25, 13, 10, 0, 0)).commonTrue(i).trueLetterCount(i).falseLetterCount(0).limitSecondRegular(60).user(user1).build());
            }
            System.out.println("Admin qo'shildi");
        }
    }
}