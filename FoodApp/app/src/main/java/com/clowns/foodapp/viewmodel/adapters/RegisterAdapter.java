package com.clowns.foodapp.viewmodel.adapters;

import android.text.TextUtils;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.clowns.foodapp.BR;
import com.clowns.foodapp.model.fisebase.User;

import java.util.HashMap;

public class RegisterAdapter  extends BaseObservable {
    private String fullName;
    private String emailRegister;
    private String passwordRegister;
    public RegisterAdapter() {
    }
    @Bindable
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
        notifyPropertyChanged(BR.fullName);
    }
    @Bindable
    public String getEmailRegister() {
        return emailRegister;
    }

    public void setEmailRegister(String emailRegister) {
        this.emailRegister = emailRegister;
        notifyPropertyChanged(BR.emailRegister);
    }

    @Bindable
    public String getPasswordRegister() {
        return passwordRegister;
    }

    public void setPasswordRegister(String passwordRegister) {
        this.passwordRegister = passwordRegister;
        notifyPropertyChanged(BR.passwordRegister);
    }

    public boolean isValidEmail(){
        return !TextUtils.isEmpty(emailRegister) && Patterns.EMAIL_ADDRESS.matcher(emailRegister).matches();
    }

    public boolean isValidPassword(){
        return !TextUtils.isEmpty(passwordRegister) && passwordRegister.length()>=8;
    }
    public User getUser(){
        return new User(fullName, emailRegister, passwordRegister, new HashMap<>(), new HashMap<String, Object>());
    }

    public boolean checkRegister(){
        if(isValidEmail() && isValidPassword()) {
            return true;
        }
        return false;
    }

}
