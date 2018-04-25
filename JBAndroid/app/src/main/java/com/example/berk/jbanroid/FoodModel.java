package com.example.berk.jbanroid;

public class FoodModel {

    private int image;
    private String name,place,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FoodModel(int image, String name, String place, String id) {
        this.image = image;
        this.name = name;
        this.place = place;
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
