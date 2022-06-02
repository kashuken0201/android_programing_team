package com.clowns.foodapp.model;

// Created by PhatTT
public class User {
    private String avatar;
    private String username;
    private String firstname;
    private String lastname;
    private String fullname;
    private String password;
    private String email;
    private String phone;

    public User(String avatar, String username, String firstname, String lastname, String password, String email, String phone) {
        this.avatar = avatar;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = this.firstname + this.lastname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
