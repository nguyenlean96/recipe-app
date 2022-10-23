package ca.gbc.comp3095.services;

import ca.gbc.comp3095.models.UserRecipe;
import ca.gbc.comp3095.repositories.UserRecipeRepository;
import ca.gbc.comp3095.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRecipeService {
//*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: service class to handle business logic for user and implement the abstract methods declared or inherited in the User Repository interface
// *********************************************************************************//

    private final UserRecipeRepository userRecipeRepository;

    @Autowired
    public UserRecipeService(UserRecipeRepository userRecipeRepository) {
        this.userRecipeRepository = userRecipeRepository;
    }

    // create, update, delete, find, findAll
    public UserRecipe create(UserRecipe object) {
        return userRecipeRepository.save(object);
    }

    public UserRecipe findById(Long id) {
        return userRecipeRepository.findById(id).orElse(null);
    }

    public UserRecipe save(UserRecipe object) {
        return userRecipeRepository.save(object);
    }

    public void delete(UserRecipe object) {
        userRecipeRepository.delete(object);
    }

    public void deleteById(Long id) {
        userRecipeRepository.deleteById(id);
    }

    public Iterable<UserRecipe> findAll() {
        return userRecipeRepository.findAll();
    }
}
