package com.kodymskiins.spring.task_spring_security.service;



import com.kodymskiins.spring.task_spring_security.model.Role;

import java.util.Set;

public interface RoleService {
    Role findByName(String name);

    Set<Role> findAll();
    void save(Role role);

}
