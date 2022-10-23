package ca.gbc.comp3095.repositories;

import ca.gbc.comp3095.models.UserRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRecipeRepository extends JpaRepository<UserRecipe, Long> {
//*********************************************************************************
//* Project: Your Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: interface extending JPA repository to allow for CRUD operations on the user_recipe table
//* this would be used for cookbook and my recipe operations depending on the value of isSaved and isCreated fields in the user_recipe table respectively
//* this distinction is made on the front layer of the application
// *********************************************************************************//
}
