package com.kaishengit.pojo;

public class Order {

    private String id;
    private String state;
    private Float price;

    public Order() {
    }

    public Order(String id, String state, Float price) {
        this.id = id;
        this.state = state;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
