package com.clowns.foodapp.model;

import java.util.List;

public class FoodCart {
    private  Food food;
    private int amount;
    private List<ChoiseCartItem> choiseCartItemList;

    public FoodCart(Food food, int amount, List<ChoiseCartItem> choiseCartItemList) {
        this.food = food;
        this.amount = amount;
        this.choiseCartItemList = choiseCartItemList;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<ChoiseCartItem> getChoiseCartItemList() {
        return choiseCartItemList;
    }

    public void setChoiseCartItemList(List<ChoiseCartItem> choiseCartItemList) {
        this.choiseCartItemList = choiseCartItemList;
    }
    public float getPrice(){
        float price = 0;
        for (ChoiseCartItem choiseCartItem : choiseCartItemList) {
            price += choiseCartItem.getPrice();
        }
        price = Math.round((price+food.getPrice()) * amount * 100)/100.0f;
        return price;
    }
}
