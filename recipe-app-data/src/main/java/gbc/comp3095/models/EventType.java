package gbc.comp3095.models;

public enum EventType {
    //*********************************************************************************
    //* Project: Your Recipe App
    //* Assignment: assignment 2
    //* Author(s): Sarah Sami - Le An Nguyen - Farshad Jalali Ameri - Angela Efremova
    //* Student Number: 101334588 - 101292266 - 101303158 - 101311327
    //* Date: 2022-11-28
    //* Description: the event type enum class is used to store the event type options for each meal plan - if it is left null then it is simply a meal plan
    //* However, if an event type is selected then it is an event plan (i.e. a meal plan for a specific event)
    // *********************************************************************************//
    BIRTHDAY ("Birthday"),
    WEDDING ("Wedding"),
    ANNIVERSARY ("Anniversary"),
    THANKSGIVING ("Thanksgiving"),
    CHRISTMAS ("Christmas"),
    HALLOWEEN ("Halloween"),
    EASTER ("Easter"),
    OTHER ("Other");

    private final String name;

    EventType(String name) {
        this.name = name;
    }
}
