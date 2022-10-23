package ca.gbc.comp3095.repositories;

import ca.gbc.comp3095.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//*********************************************************************************
//* Project: The Recipe App
//* Assignment: assignment 1
//* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
//* Student Number: 101334588 - 101292266 - 101303158 - 101311327
//* Date: 2022-10-23
//* Description: interface extending JPA repository to allow for CRUD operations on the user table
// *********************************************************************************//
    User findByUsername(String username);
}

