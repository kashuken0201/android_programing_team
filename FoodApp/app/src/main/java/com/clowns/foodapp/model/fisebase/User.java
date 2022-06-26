package com.clowns.foodapp.model.fisebase;

import android.text.TextUtils;
import android.util.Patterns;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable {
    private String userId;
    private String fullName;
    private String email;
    private String password;
    private HashMap<String, Object> cart;
    private HashMap<String, Object> favourite;

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

    public boolean isValidEmail(){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPassword(){
        return !TextUtils.isEmpty(password) && password.length()>=8;
    }
}
