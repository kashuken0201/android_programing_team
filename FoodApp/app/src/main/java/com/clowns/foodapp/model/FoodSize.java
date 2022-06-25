package com.clowns.foodapp.model;

public class FoodSize {
    private String foodSizeId;
    private String sizeName;
    private Double coefficient;

    public FoodSize(String sizeName, Double coefficient) {
        this.sizeName = sizeName;
        this.coefficient = coefficient;
    }

    public String getFoodSizeId() {
        return foodSizeId;
    }

    public void setFoodSizeId(String foodSizeId) {
        this.foodSizeId = foodSizeId;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public Double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
}
