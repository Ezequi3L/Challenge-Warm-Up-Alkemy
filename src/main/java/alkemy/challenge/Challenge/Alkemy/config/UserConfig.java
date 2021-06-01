package alkemy.challenge.Challenge.Alkemy.config;

import alkemy.challenge.Challenge.Alkemy.model.UserEntity;
import alkemy.challenge.Challenge.Alkemy.repository.UserEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class UserConfig {

//    private BCryptPasswordEncoder encoder;

    @Bean
    CommandLineRunner commandLineRunner(UserEntityRepository repository) {
        return args -> {
            //User user1 = new User("user","alkemy@email.com", encoder.encode("user"));
            //User user2 = new User("admin","alkemy@email.com", encoder.encode("admin"));
            UserEntity user1 = new UserEntity("user", "alkemy@email.com", "user");
            UserEntity user2 = new UserEntity("admin", "alkemy@email.com", "admin");
            repository.saveAll(List.of(user1, user2));
        };
    }
}
