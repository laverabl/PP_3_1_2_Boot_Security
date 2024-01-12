package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private  final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String showUser(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.getUserByName(username);
        model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());
        return "admin-show";
    }



    @GetMapping("new")
    public String createNewUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("listRoles", roleService.getListRoles());
        return "admin-new";
    }

    @PostMapping("")
    public String create(@ModelAttribute("user") User user) {
        userService.newUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.newUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/delete")
    public String formDel(Model model, @PathVariable("id") int id, Model roles) {
        roles.addAttribute("listRoles", roleService.getListRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "admin-delete";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }


}
