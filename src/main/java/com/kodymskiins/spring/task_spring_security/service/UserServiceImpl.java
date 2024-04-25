package com.kodymskiins.spring.task_spring_security.service;

import com.kodymskiins.spring.task_spring_security.dao.UserRepository;
import com.kodymskiins.spring.task_spring_security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

//    @Override
//    public void create(User user) {
//
//    }

    @Override
    public User read(Long id) {
        User user = null;
        Optional<User> OptUser = userRepository.findById(id);

        if(OptUser.isPresent()){
            user = OptUser.get();
        }
        return user;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return  userRepository.findAll();
    }


    @Override
    public List<User> getList() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByName(String name) {
        List<User> users = userRepository.findAllByFirstName(name);
        return users;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }




  /*  @Override
    public void deleteById(Class<?> entityClass, Object id) {
        userDao.deleteById(entityClass, id);
    }

    @Override

    public void deleteUserTregulov(Long id) {
        userDao.deleteUserTregulov(id);
    }*/

}
