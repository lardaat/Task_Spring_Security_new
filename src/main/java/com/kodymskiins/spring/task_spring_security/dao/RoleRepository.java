package com.kodymskiins.spring.task_spring_security.dao;

import com.kodymskiins.spring.task_spring_security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
