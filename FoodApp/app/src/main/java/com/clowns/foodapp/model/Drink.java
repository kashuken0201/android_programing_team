package com.clowns.foodapp.model;

public class Drink {
    private String drinkId;
    private String drinkName;
    private double price;
    private String description;
    private String url;

    public Drink(String drinkName, double price, String description, String url) {
        this.drinkName = drinkName;
        this.price = price;
        this.description = description;
        this.url = url;
    }

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
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
