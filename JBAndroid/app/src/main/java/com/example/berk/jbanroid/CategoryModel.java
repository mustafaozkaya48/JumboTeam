package com.example.berk.jbanroid;

import android.graphics.Bitmap;

public class CategoryModel {

    private String Title;
    private String Category;

    public String getTitle() {
        return Title;
    }

    public String getCategory() {
        return Category;
    }

    public String getDescription() {
        return Description;
    }

    public Bitmap getThumbnail() {
        return Thumbnail;
    }

    private String Description;

    public void setTitle(String title) {
        Title = title;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(Bitmap thumbnail) {
        Thumbnail = thumbnail;
    }

    private Bitmap Thumbnail;


    public CategoryModel() {


    }

    public CategoryModel(String title, String category, String description, Bitmap ImageUrl) {
        Title = title;
        Category = category;
        Description = description;
        Thumbnail = ImageUrl;
    }


}
