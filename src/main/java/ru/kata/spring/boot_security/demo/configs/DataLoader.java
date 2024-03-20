package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

@Component
public class DataLoader implements CommandLineRunner{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = roleRepository.findByRole("ADMIN");
        if (adminRole == null) {
            adminRole = roleRepository.save(new Role("ADMIN"));
        }

        Role userRole = roleRepository.findByRole("USER");
        if (userRole == null) {
            userRole = roleRepository.save(new Role("USER"));
        }

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        adminUser.addRole(adminRole);
        adminUser.addRole(userRole);
        userRepository.save(adminUser);

        User userUser = new User();
        userUser.setUsername("user");
        userUser.setPassword(passwordEncoder.encode("user"));
        userUser.addRole(userRole);
        userRepository.save(userUser);
    }
}
