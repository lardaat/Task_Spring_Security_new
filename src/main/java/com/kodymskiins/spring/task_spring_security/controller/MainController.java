package com.kodymskiins.spring.task_spring_security.controller;

import com.kodymskiins.spring.task_spring_security.model.User;
import com.kodymskiins.spring.task_spring_security.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {

    @GetMapping("/loginPage")
    public String showLoginPage(HttpServletRequest request, Model model) {
        if (request.getSession().getAttribute("error") != null) {
            model.addAttribute("errorMessage", "Неправильное имя пользователя или пароль.");
            request.getSession().removeAttribute("error"); // очистить ошибку, чтобы она не оставалась в сессии
        }

        return "loginPage";
    }

    @GetMapping("/admin/adminStart")
    public String adminDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if(userDetails != null) {
            String firstName = ((User) userDetails).getFirstName();
            model.addAttribute("firstName", firstName);
        }
        return "admin/adminStart";
    }

    @GetMapping("/user/userStart")
    public String userDashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if(userDetails != null) {
            String firstName = ((User) userDetails).getFirstName();
            model.addAttribute("firstName", firstName);
        }
        return "user/userStart";
    }


    private final CustomUserDetailsService userDetailsService;

    @Autowired
    public MainController(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/loginPage") // Обработка POST-запроса на /loginPage
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {
        try {
            // Попытка загрузки данных пользователя из CustomUserDetailsService
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);


            if (userDetails.getPassword().equals(password)) {

                return "redirect:/admin/adminStart";
            } else {

                model.addAttribute("errorMessage", "Неправильный пароль");
                return "loginPage";
            }
        } catch (UsernameNotFoundException e) {

            model.addAttribute("errorMessage", "Пользователь с таким именем не найден");
            return "loginPage";
        }
    }


//    @GetMapping("/admin/adminStart")
//    public String adminDashboard(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String firstName = null;
//
//        if (auth.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) auth.getPrincipal();
//            firstName = ((User) userDetails).getFirstName();
//        }
//
//        model.addAttribute("firstName", firstName);
//        return "admin/adminStart";
//    }
//
//    @GetMapping("/user/userStart")
//    public String userDashboard(Model model) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String firstName = null;
//
//        if(auth.getPrincipal() instanceof UserDetails) {
//            UserDetails userDetails = (UserDetails) auth.getPrincipal();
//            firstName = ((User) userDetails).getFirstName();
//        }
//
//        model.addAttribute("firstName", firstName);
//        return "user/userStart";
//    }
}