package com.clowns.foodapp.model;

<<<<<<< HEAD
public class Food {
    private String name;
    private float price;
    private String urlImage;

    public Food(String name, float price, String urlImage) {
        this.name = name;
        this.price = price;
        this.urlImage = urlImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
=======
import java.util.Arrays;
import java.util.Map;

public class Food {
    private String foodId;
    private String foodName;
    private String foodType;
    private double price;
    private String description;
    private String url;

    public Food(String foodName, String foodType, double price, String description, String url) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.price = price;
        this.description = description;
        this.url = url;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;

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
>>>>>>> origin/dev
    }
}
