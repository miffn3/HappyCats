package university.happyCatsSpring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import university.happyCatsSpring.entity.User;
import university.happyCatsSpring.repo.UserRepository;

@Component
@Slf4j
public class DemoData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        String encodedPassword = new BCryptPasswordEncoder().encode("password");
        User user1 = new User("user1", encodedPassword, "name", "user1@mail.com");
        userRepository.save(user1);

        User user2 = new User("user2", encodedPassword, "name", "user2@mail.com");
        userRepository.save(user2);

        log.debug("printing all users...");
        this.userRepository.findAll().forEach(v -> log.debug(" User :{}", v));
    }
}