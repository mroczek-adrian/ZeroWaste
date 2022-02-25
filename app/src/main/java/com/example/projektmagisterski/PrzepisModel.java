package com.example.projektmagisterski;

public class PrzepisModel {

    private int przepis_id;
    private String przepis_name;
    private String skladnik_name1;
    private String skladnik_name2;
    private String skladnik_name3;

    public PrzepisModel(int przepis_id, String przepis_name, String skladnik_name1, String skladnik_name2, String skladnik_name3) {
        this.przepis_id = przepis_id;
        this.przepis_name = przepis_name;
        this.skladnik_name1 = skladnik_name1;
        this.skladnik_name2 = skladnik_name2;
        this.skladnik_name3 = skladnik_name3;
    }

    public PrzepisModel() {
    }



    @Override
    public String toString() {
        return "PrzepisModel{" +
                "przepis_id=" + przepis_id +
                ", przepis_name='" + przepis_name + '\'' +
                ", skladnik_name1='" + skladnik_name1 + '\'' +
                ", skladnik_name2='" + skladnik_name2 + '\'' +
                ", skladnik_name3='" + skladnik_name3 + '\'' +
                '}';
    }

    public int getPrzepis_id() {
        return przepis_id;
    }

    public void setPrzepis_id(int przepis_id) {
        this.przepis_id = przepis_id;
    }

    public String getPrzepis_name() {
        return przepis_name;
    }

    public void setPrzepis_name(String przepis_name) {
        this.przepis_name = przepis_name;
    }

    public String getSkladnik_name1() {
        return skladnik_name1;
    }

    public void setSkladnik_name1(String skladnik_name1) {
        this.skladnik_name1 = skladnik_name1;
    }

    public String getSkladnik_name2() {
        return skladnik_name2;
    }

    public void setSkladnik_name2(String skladnik_name2) {
        this.skladnik_name2 = skladnik_name2;
    }

    public String getSkladnik_name3() {
        return skladnik_name3;
    }

    public void setSkladnik_name3(String skladnik_name3) {
        this.skladnik_name3 = skladnik_name3;
    }
}
