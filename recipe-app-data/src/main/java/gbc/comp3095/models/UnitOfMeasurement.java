package gbc.comp3095.models;

public enum UnitOfMeasurement {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588 - 101292266 - 101303158 - 101311327
    //* Date: 2022-11-28
    //* Description: the unit of measurement enum class is used to store the unit of measurement options for each ingredient
    // *********************************************************************************//
    CUP ("Cup"),
    TABLESPOON  ("Tablespoon"),
    TEASPOON ("Teaspoon"),
    GRAM ("Gram"),
    MILLILITER ("Milliliter"),
    LITER ("Liter"),
    POUND ("Pound"),
    OUNCE ("Ounce"),
    KILOGRAM ("Kilogram"),
    UNIT ("Unit");

    private final String name;

    UnitOfMeasurement(String name) {
        this.name = name;
    }
}
