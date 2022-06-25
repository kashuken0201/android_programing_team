package com.clowns.foodapp.model;

import android.text.TextUtils;
import android.util.Patterns;
//create by NhanLT
public class User {
    private String email;
    private String password;

    public User(String email,String password){
        this.email=email;
        this.password=password;
    }

    public String getUsername() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidEmail(){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPassword(){
        return !TextUtils.isEmpty(password) && password.length()>=8;
    }
}
