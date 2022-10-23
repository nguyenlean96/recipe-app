package ca.gbc.comp3095.configurations;

import ca.gbc.comp3095.models.User;
import ca.gbc.comp3095.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
//*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: configuration class to load some user data into the database as application is started up
// *********************************************************************************//
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository userRepository) {
        return args -> {
            User user1 = new User("sarah.sami", "iamawesomebutvertired", "sarah7sami@gmail.com",
                    "Sarah", "Sami", "437-279-0000");
            user1.encryptPassword();
            userRepository.save(user1);


            User user2 = new User();
            user2.setUsername("lean.96");
            user2.setPassword(("leanisawesometooeveryoneelsesucks"));
            user2.encryptPassword();
            user2.setEmail("lean.nguyen@yahoo.com");
            user2.setPhoneNumber("647-123-4567");
            user2.setFirstName("lean");
            user2.setLastName("nguyen");
            userRepository.save(user2);
        };
    }
}
