package gbc.comp3095.services;

import gbc.comp3095.models.User;
import gbc.comp3095.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: service class to handle business logic for user and implement the abstract methods declared or
//* inherited in the User Repository interface
// *********************************************************************************//
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create, update, delete, find, findAll
    public User create(User object) {
        return userRepository.save(object);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User object) {
        return userRepository.save(object);
    }

    public void delete(User object) {
        userRepository.delete(object);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) { return userRepository.findByUsername(username); }

}