package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;


import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) {
        return userRepository.getById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}






