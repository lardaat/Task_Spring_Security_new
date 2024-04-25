package com.kodymskiins.spring.task_spring_security.RESTController;

import com.kodymskiins.spring.task_spring_security.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainRESTController {

    @GetMapping("/auth/login")
    public ResponseEntity<Map<String, String>> showLoginPage(HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();

        if (request.getSession().getAttribute("error") != null) {
            response.put("errorMessage", "Неправильное имя пользователя или пароль.");
            request.getSession().removeAttribute("error");
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }


    @GetMapping("/admin/adminStart")
    public ResponseEntity<Map<String, String>> adminDashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> response = new HashMap<>();

        if (auth.getPrincipal() instanceof UserDetails userDetails) {
            String firsName = ((User) userDetails).getFirstName();
            response.put("firstName", firsName);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/userStart")
    public ResponseEntity<Map<String, String>> userDashboard() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, String> response = new HashMap<>();

        if(auth.getPrincipal() instanceof UserDetails userDetails) {
            String firstName = ((User) userDetails).getFirstName();
            response.put("firstName", firstName);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
