package com.clowns.foodapp.model.fisebase;

import java.io.Serializable;

public class FoodDrink implements Serializable {
    private String id;
    private String foodName;
    private String categoryName;
    private double price;
    private String description;
    private String url;

    public FoodDrink() {

    }

    public FoodDrink(String foodName, String categoryName, double price, String description, String url) {
        this.foodName = foodName;
        this.categoryName = categoryName;
        this.price = price;
        this.description = description;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
