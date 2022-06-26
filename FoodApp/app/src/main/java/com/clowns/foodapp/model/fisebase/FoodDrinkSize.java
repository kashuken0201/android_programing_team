package com.clowns.foodapp.model.fisebase;

public class FoodDrinkSize {
    private String sizeId;
    private String sizeName;
    private Double coef;

    public FoodDrinkSize() {
    }

    public FoodDrinkSize(String sizeName, Double coef) {
        this.sizeName = sizeName;
        this.coef = coef;
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

    public Double getCoef() {
        return coef;
    }

    public void setCoef(Double coef) {
        this.coef = coef;
    }
}
