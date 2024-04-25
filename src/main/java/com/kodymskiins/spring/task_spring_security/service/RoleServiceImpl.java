package com.kodymskiins.spring.task_spring_security.service;

import com.kodymskiins.spring.task_spring_security.dao.RoleRepository;
import com.kodymskiins.spring.task_spring_security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) { this.roleRepository = roleRepository; }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public Set<Role> findAll() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
