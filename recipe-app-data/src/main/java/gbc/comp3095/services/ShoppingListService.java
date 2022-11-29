package gbc.comp3095.services;

import gbc.comp3095.models.ShoppingList;
import gbc.comp3095.repositories.ShoppingListRepository;

public class ShoppingListService {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588 - 101292266 - 101303158 - 101311327
    //* Date: 2022-11-28
    //* Description: service class to handle business logic for shopping list
    // *********************************************************************************//

    private final ShoppingListRepository shoppingListRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    // create, update, delete, find, findAll
    public ShoppingList create(ShoppingList object) {
        return shoppingListRepository.save(object);
    }

    public ShoppingList findById(Long id) {
        return shoppingListRepository.findById(id).orElse(null);
    }

    public ShoppingList save(ShoppingList object) {
        return shoppingListRepository.save(object);
    }

    public void delete(ShoppingList object) {
        shoppingListRepository.delete(object);
    }

    public void deleteById(Long id) {
        shoppingListRepository.deleteById(id);
    }

    public Iterable<ShoppingList> findAll() {
        return shoppingListRepository.findAll();
    }
}
