package com.clowns.foodapp.model;

public class Food {
    private String name;
    private String description;
    private double price;
    private String img;

    public Food(String name, String description, double price, String img) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
