package com.kodymskiins.spring.task_spring_security.init;

import com.kodymskiins.spring.task_spring_security.model.Role;
import com.kodymskiins.spring.task_spring_security.model.User;
import com.kodymskiins.spring.task_spring_security.service.RoleService;
import com.kodymskiins.spring.task_spring_security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataInitializer {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;

    private final RoleService roleService;

    @Autowired
    public DataInitializer(UserService userService, BCryptPasswordEncoder encoder, RoleService roleService) {
        this.userService = userService;
        this.encoder = encoder;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        List<User> users = userService.findAll();

        if (users.isEmpty()) {
            // add roles
            Role adminRole = roleService.findByName("ROLE_ADMIN");
            if (adminRole == null) {
                adminRole = new Role("ROLE_ADMIN");
                roleService.save(adminRole);
            }
            Role userRole = roleService.findByName("ROLE_USER");
            if (userRole == null) {
                userRole = new Role("ROLE_USER");
                roleService.save(userRole);
            }
            // add admin
            User admin = new User("adminAndUser", "adminAndUser", 20, "adminAnUse@mail.com", encoder.encode("1111"));
            admin.getRoles().add(adminRole);
            admin.getRoles().add(userRole);
            userService.save(admin);

            User admin2 = new User("admin", "admin", 25, "adminr@mail.com", encoder.encode("1111"));
            admin2.getRoles().add(adminRole);
            userService.save(admin2);

            // add users
            User user1 = new User("Ivan", "User1", 30, "ivan@mail.com", encoder.encode("1234"));
            user1.getRoles().add(userRole);
            userService.save(user1);

            User user2 = new User("Kate", "User2", 35, "kate@mail.com", encoder.encode("1234"));
            user2.getRoles().add(userRole);
            userService.save(user2);
        }
    }
}
