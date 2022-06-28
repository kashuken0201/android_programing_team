package com.clowns.foodapp.model.fisebase;

import android.text.TextUtils;
import android.util.Patterns;

import com.clowns.foodapp.model.view.ChoiceItem;
import com.clowns.foodapp.model.view.FoodDrinkCart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User implements Serializable {
    private String userId;
    private String fullName;
    private String email;
    private String password;
    private HashMap<String, Object> cart;
    private HashMap<String, Object> favourite;

    private static User instance = null;
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public User() {
    }

    public User(String fullName, String email, String password, HashMap<String, Object> cart, HashMap<String, Object> favourite) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.cart = cart;
        this.favourite = favourite;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<String, Object> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, Object> cart) {
        this.cart = cart;
    }

    public HashMap<String, Object> getFavourite() {
        return favourite;
    }

    public void setFavourite(HashMap<String, Object> favourite) {
        this.favourite = favourite;
    }
    public  String getCartString(){
        String cartString = "";
        for (String key : cart.keySet()) {
            HashMap<String, Object> map = (HashMap<String, Object>) cart.get(key);
            for (String key1 : map.keySet()) {
                if (key1.equals("food")) {
                    if (!(map.get(key1) instanceof FoodDrink)){
                        return "";
                    }
                    FoodDrink foodDrink = (FoodDrink) map.get(key1);
                    cartString += foodDrink.getFoodName() + ",";
                }
                if (key1.equals("foodsize")) {
                    if (!(map.get(key1) instanceof FoodDrinkSize)){
                        return "";
                    }
                    FoodDrinkSize foodDrinkSize = (FoodDrinkSize) map.get(key1);
                    cartString += foodDrinkSize.getSizeName() + ",";
                }
                if (key1.equals("quantity")){
                    cartString += map.get(key1) + ",";
                }
                if (key1.equals("choiceItemList")) {
                    HashMap<String, Object> choiceItemMap = (HashMap<String, Object>) map.get(key1);
                    for (String keyChoice : choiceItemMap.keySet()) {
                        if (!(choiceItemMap.get(keyChoice) instanceof ChoiceItem)) {
                            return "";
                        }
                        ChoiceItem choiceItem = (ChoiceItem) choiceItemMap.get(keyChoice);
                        cartString += choiceItem.getChoiceName() + ",";
                    }
                }
            }
        }
        return cartString;
    }

    public void setUser(User user) {
        this.userId = user.userId;
        this.fullName = user.fullName;
        this.email = user.email;
        this.password = user.password;
        this.cart = user.cart;
        this.favourite = user.favourite;
    }


}
