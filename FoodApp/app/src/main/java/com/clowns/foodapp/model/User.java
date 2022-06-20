package com.clowns.foodapp.model;

import java.util.Arrays;
import java.util.Map;

public class User {
    private String userId;
    private String fullName;
    private String email;
    private String password;
    private Map<Integer, Arrays> cart;
    private Map<Integer, Arrays> favourite;

    public User(String fullName, String email, String password, Map<Integer, Arrays> cart, Map<Integer, Arrays> favourite) {
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

    public Map<Integer, Arrays> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, Arrays> cart) {
        this.cart = cart;
    }

    public Map<Integer, Arrays> getFavourite() {
        return favourite;
    }

    public void setFavourite(Map<Integer, Arrays> favourite) {
        this.favourite = favourite;
    }
}
