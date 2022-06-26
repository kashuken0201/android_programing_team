package com.clowns.foodapp.model.fisebase;

public class Category {
    String categoryId;
    String categoryName;
    int categoryUrl;

    public Category() {
    }

    public Category(String categoryName, int categoryUrl) {
        this.categoryName = categoryName;
        this.categoryUrl = categoryUrl;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(int categoryUrl) {
        this.categoryUrl = categoryUrl;
    }
}
