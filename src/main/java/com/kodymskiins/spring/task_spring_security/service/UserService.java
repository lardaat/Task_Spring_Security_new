package com.kodymskiins.spring.task_spring_security.service;





import com.kodymskiins.spring.task_spring_security.model.User;

import java.util.List;

public interface UserService {
//    void create(User user);
    User read(Long id);
    void save(User user);
    void delete(Long id);

    List<User> findAll();

//    void deleteById(Class<?> entityClass, Object id);
//
//    void deleteUserTregulov(Long id);
    List<User> getList();

    public List<User> findAllByName(String name);

    User findByUsername(String username);
}
