package com.example.berk.jbanroid;

public class OrderModel {
    private int id;
    private String productName;
    private Double productPrice;
    private int Count;
    private String Porsion;
    private String State;

    public String getPorsion() {
        return Porsion;
    }
    public void setPorsion(String porsion) {
        Porsion = porsion;
    }
    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

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

    public OrderModel(int id, String productName, Double productPrice, int count,String porsion,String state) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.Porsion=porsion;
        this.State=state;
        this.Count = count;
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
