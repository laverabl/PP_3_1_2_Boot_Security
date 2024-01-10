package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.Set;

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


    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") int id, Model roles) {
        roles.addAttribute("listRoles", roleService.getListRoles());
        model.addAttribute("user", userService.getUserById(id));
        return "admin-edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @RequestParam("roles") Set<String> roles) {
        Set<Role> roles = roles.stream().map(roleService::getRoleByName).collect(Collectors.toSet());
        user.setRoles(roles);
        userService.newUser(user);
        return "redirect:/admin";
    }
//
//
//    @GetMapping("new")
//    public String createNewUser(@ModelAttribute("user") User user, Model model) {
//        model.addAttribute("listRoles", roleService.getListRoles());
//        return "admin-new";
//    }
//
//    @PostMapping("/users")
//    public String create(@ModelAttribute("user") User user) {
//        userService.newUser(user);
//        return "redirect:/admin/users";
//    }
//
//    @GetMapping("/users/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id, Model roles) {
//        roles.addAttribute("listRoles", roleService.getListRoles());
//        model.addAttribute("user", userService.getUserById(id));
//        return "admin-edit";
//    }
//

//
//    @DeleteMapping("users/{id}")
//    public String delete(@PathVariable("id") int id) {
//        userService.deleteUserById(id);
//        return "redirect:/admin/users";
//    }


}
