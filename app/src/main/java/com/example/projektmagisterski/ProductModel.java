package com.example.projektmagisterski;

import java.util.Date;

public class ProductModel {
    private int id;
    private String name;
    private String dateTime;
    private int amount;
    private boolean isActive;


    //constructor


    public ProductModel(int id, String name,  String dateTime, int amount, boolean isActive ) {
        this.id = id;
        this.name = name;
        this.dateTime = dateTime;
        this.amount = amount;
        this.isActive = isActive;
    }

    public ProductModel() {

    }


    //toString is necessary for printing the contents of the class object

    @Override
    public String toString() {
        return ""+name  + "\n"+
                " Liczba sztuk: " +amount+"\n"+
                " Data ważności: "+dateTime+"\n";
    }
//    @Override
//    public String toString() {
//        return "ProdukcikProductModel{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                " dateTime="+dateTime+
//                ", amount=" + amount +
//                ", isActive=" + isActive +
//                '}';
//    }
// return "" + przepis_name + "\n"+
//            "   Składnik 1 : " + skladnik_name1 + "\n" +
//            "   Składnik 2 :  " + skladnik_name2 + "\n" +
//            "   Składnik 3 :  " + skladnik_name3 + "\n";

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.name = dateTime;
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
