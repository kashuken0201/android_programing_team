package com.clowns.foodapp.model;

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
    }
}
