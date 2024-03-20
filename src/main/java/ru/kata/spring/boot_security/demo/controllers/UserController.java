package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.entities.User;

@RestController
@RequestMapping("api/user")
public class UserController {

    @GetMapping("")
    public ResponseEntity<User> showAuthUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }
}

