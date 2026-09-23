package com.updatetracker.update_tracker.config;

import com.updatetracker.update_tracker.model.Role;
import com.updatetracker.update_tracker.model.User;
import com.updatetracker.update_tracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();

            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password"));
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);

            System.out.println("=================================");
            System.out.println("Default ADMIN user created");
            System.out.println("Username: admin");
            System.out.println("Password: password");
            System.out.println("=================================");
        }
    }
}
