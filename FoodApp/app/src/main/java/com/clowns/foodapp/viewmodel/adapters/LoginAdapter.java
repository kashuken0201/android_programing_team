package com.clowns.foodapp.viewmodel.adapters;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;


import com.clowns.foodapp.BR;
import com.clowns.foodapp.model.fisebase.User;
import com.clowns.foodapp.viewmodel.UserViewModel;

import java.util.HashMap;
import java.util.List;


public class LoginAdapter extends BaseObservable {
    private String email;
    private String password;
    public ObservableField<String> message= new ObservableField<>("Login");
    public LoginAdapter() {
        this.email = "a@gmail.com";
        this.password = "123456789";
    }
    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public boolean isValidEmail(){
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isValidPassword(){
        return !TextUtils.isEmpty(password) && password.length()>=8;
    }

    public boolean checkLogin(User user){
        if(email.isEmpty() || password.isEmpty()){
            message.set("Please fill all field");
            return false;
        }

        //Log.d("DEBUG", "checkLogin: " + listUser.size());
        String fullname = "";
        HashMap<String, Object> cart = new HashMap<>();
        HashMap<String, Object> favourite = new HashMap<>();

        if(isValidEmail() && isValidPassword()) {
                if (user.getEmail().equals(getEmail()) && user.getPassword().equals(getPassword())) {
                    message.set("Login Success");
                    return true;
                }
        }
        else {
            message.set("Failed");
            return false;
        }
        return  false;
    }

}

