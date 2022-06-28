package com.clowns.foodapp.model.view;

import android.util.Log;

import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.model.fisebase.FoodDrinkSize;

import java.io.Serializable;
import java.util.List;

public class FoodDrinkCart implements Serializable {
    private FoodDrink item;
    private FoodDrinkSize size;
    private int quantity;
    private List<ChoiceItem> choiceItemList;

    public FoodDrinkCart(){

    }
    public FoodDrinkCart(FoodDrink item, FoodDrinkSize size, int quantity, List<ChoiceItem> choiceItemList) {
        this.item = item;
        this.size = size;
        this.quantity = quantity;
        this.choiceItemList = choiceItemList;
    }

    public FoodDrink getItem() {
        return item;
    }

    public void setItem(FoodDrink item) {
        this.item = item;
    }

    public FoodDrinkSize getSize() {
        return size;
    }

    public void setSize(FoodDrinkSize size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<ChoiceItem> getChoiceCartItemList() {
        return choiceItemList;
    }

    public void setChoiceItemList(List<ChoiceItem> choiceItemList) {
        this.choiceItemList = choiceItemList;
    }

    public double getPrice(){
        double price = 0;
        for (ChoiceItem choiceItem : choiceItemList) {
            price += choiceItem.getChoicePrice();
        }
        price = Math.round((price + item.getPrice()+size.getCoefficient()) * quantity * 100)/100.0f;
        return price;
    }
    public String getString(){
        String str = "";
        for (ChoiceItem choiceItem : choiceItemList) {
            str += choiceItem.getChoiceName() + " ";
        }
        str += item.getFoodName() + " " + size.getSizeName() + " " + quantity + " " + getPrice();
        return str;
    }
}
