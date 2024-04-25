package com.kodymskiins.spring.task_spring_security.dao;

import com.kodymskiins.spring.task_spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAllByFirstName(String firstName);

    User findByUsername(String username);

}
