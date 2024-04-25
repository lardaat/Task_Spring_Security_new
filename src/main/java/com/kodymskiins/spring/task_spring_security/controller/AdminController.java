package com.kodymskiins.spring.task_spring_security.controller;

import com.kodymskiins.spring.task_spring_security.model.Role;
import com.kodymskiins.spring.task_spring_security.model.User;
import com.kodymskiins.spring.task_spring_security.service.RoleService;
import com.kodymskiins.spring.task_spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("admin/users")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, BCryptPasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String showAllUsers(Model model){
        List<User> users = userService.findAll();
        Set<Role> roles =roleService.findAll();
        List<Role> allRoles = new ArrayList<>(roles);

        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", allRoles);

        return "admin/users/all-users";
    }

    @PostMapping // save
    public String showUserDetails(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users";
    }


    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.read(id);
        Set<Role> roles = roleService.findAll();
        List<Role> allRoles = new ArrayList<>(roles);
        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        return "admin/users/show-user-view";
    }

//    @GetMapping("/create")
//    public String createUserForm(Model model) {
//        Set<Role> roles = roleService.findAll();
//        List<Role> allRoles = new ArrayList<>(roles);
//        model.addAttribute("user", new User());
//        model.addAttribute("allRoles", allRoles);
//        return "admin/users/user-view";
//    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin/users/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/users/";
    }

    @RequestMapping("/viewDelete")
    public String viewDelete(@ModelAttribute("user") User user) {

        return "/admin/users/delete";
    }

    @GetMapping("/askDetails")
    public String askUserDetails(Model model) {
        Set<Role> roles = roleService.findAll();
        List<Role> allRoles = new ArrayList<>(roles);
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", allRoles);

        return "admin/users/user-view";
    }

//    @GetMapping("/redirectHome")
//    public String redirectHome() {
//        return "redirect:/admin/users/";
//    }
}

