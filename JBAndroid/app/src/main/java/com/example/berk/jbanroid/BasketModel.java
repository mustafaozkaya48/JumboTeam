package com.example.berk.jbanroid;

public class BasketModel {
    private int id;
    private String productName;
    private Double productPrice;
    private int Count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public BasketModel(int id, String productName, Double productPrice, int count) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        Count = count;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }
}
