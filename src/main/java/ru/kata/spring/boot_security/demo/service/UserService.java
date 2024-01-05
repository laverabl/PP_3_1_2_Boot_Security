package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

@Repository
public interface UserService extends UserDetailsService {

    void newUser(User user);
    void deleteUserById(int id);
    List<User> getAllUsers();
    User getUserById(int id);
    User getUserByName(String name);
    User findByUsername(String username);

}


