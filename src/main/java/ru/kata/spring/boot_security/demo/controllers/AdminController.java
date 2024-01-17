package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> showUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/newUser")
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        userService.newUser(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User updatedUser) {
//        User user = userService.getUserById(id);
//        if (user != null) {
//            user.setEmail(updatedUser.getEmail());
//            userService.newUser(user);
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable("id") int id) {
//        userService.deleteUserById(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}

