package com.example.berk.jbanroid;

import android.graphics.Bitmap;

public class FoodModel {

    private Bitmap image;
    private String name,place,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public FoodModel(Bitmap image, String name, String place, String id) {
        this.image = image;
        this.name = name;
        this.place = place;
        this.id = id;
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
