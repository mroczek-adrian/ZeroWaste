package com.example.projektmagisterski;

public class CustomerModel {
    private int id;
    private String name;
    private int age;
    private boolean isActive;


    //constructor

    public CustomerModel(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }
}
