package com.example.projektmagisterski;

public class ProductModel {
    private int id;
    private String name;
    private int amount;
    private boolean isActive;


    //constructor


    public ProductModel(int id, String name, int amount, boolean isActive) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.isActive = isActive;
    }

    public ProductModel() {

    }

    //toString is necessary for printing the contents of the class object

    @Override
    public String toString() {
        return "ProdukcikProductModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", isActive=" + isActive +
                '}';
    }


    //gettter and setter


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int age) {
        this.amount = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
