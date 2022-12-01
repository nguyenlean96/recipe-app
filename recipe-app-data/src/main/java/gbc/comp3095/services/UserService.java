package gbc.comp3095.services;

import gbc.comp3095.models.User;
import gbc.comp3095.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create, update, delete, find, findAll
    public User create(User object) {
        return userRepository.save(object);
    }

    public User findById(Long id) {
        List<User> saved_users = (List<User>) this.userRepository.findAll();
        for (User u : saved_users) {
            if (u.getId().equals(id))
                return u;
        }
        return null;
    }

    public User save(User object) {
        return this.userRepository.save(object);
    }

    public void delete(User object) {
        if ((this.findById(object.getId()) != null) || (this.findByUsername(object.getUsername()) != null))
            this.userRepository.delete(object);
    }

    public void deleteById(Long id) {
        User found_user = this.findById(id);
        if (found_user != null)
            userRepository.deleteById(id);
    }

    public Iterable<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findByUsername(String username) {
        List<User> saved_users = (List<User>) this.userRepository.findAll();
        for (User u : saved_users) {
            if (u.getUsername().equals(username))
                return u;
        }
        return null;
    }

}