package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

@Repository
public interface UserService{

    boolean save(User user);

    void delete(long id);

    List<User> findAll();

    User findById(long id);

    void update(User user);
}



