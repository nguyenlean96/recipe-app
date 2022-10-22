package ca.gbc.comp3095.configurations;

import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository userRepository) {
        return args -> {
            User user1 = new User("sarah.sami", "iamawesomebutvertired", "sarah7sami@gmail.com",
                    "Sarah", "Sami", "437-279-0000");
            user1.addDefaultCookbook();
            userRepository.save(user1);


            User user2 = new User();
            user2.setUsername("lean.96");
            user2.setPassword(("leanisawesometooeveryoneelsesucks"));
            user2.setEmail("lean.nguyen@yahoo.com");
            user2.setPhoneNumber("647-123-4567");
            user2.setFirstName("lean");
            user2.setLastName("nguyen");
            userRepository.save(user2);
        };
    }
}
