package alkemy.challenge.Challenge.Alkemy.config;

import alkemy.challenge.Challenge.Alkemy.model.UserEntity;
import alkemy.challenge.Challenge.Alkemy.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(UserEntityRepository repository) {
        return args -> {
            //User user1 = new User("user","alkemy@email.com", encoder.encode("user"));
            //User user2 = new User("admin","alkemy@email.com", encoder.encode("admin"));
            UserEntity user1 = new UserEntity("user", "alkemy@email.com", passwordEncoder.encode("user"));
            UserEntity user2 = new UserEntity("admin", "alkemy@email.com", passwordEncoder.encode("admin"));
            repository.saveAll(List.of(user1, user2));
        };
    }
}
