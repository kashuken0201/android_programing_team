package com.clowns.foodapp.model.fisebase;

public class FoodDrinkSize {
    private String sizeId;
    private String sizeName;
    private Double coefficient;

    public FoodDrinkSize() {
    }

    public FoodDrinkSize(String sizeName, Double coef) {
        this.sizeName = sizeName;
        this.coefficient = coef;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
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
