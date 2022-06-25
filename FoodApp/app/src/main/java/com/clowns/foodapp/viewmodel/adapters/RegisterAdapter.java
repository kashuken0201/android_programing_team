package com.clowns.foodapp.viewmodel.adapters;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.clowns.foodapp.BR;
import com.clowns.foodapp.model.User;

public class RegisterAdapter  extends BaseObservable {
    private String emailRegister;
    private String passwordRegister;

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

    public boolean checkRegister(){
//        User user = new User(getEmailRegister(),getPasswordRegister());
//        if(user.isValidEmail() && user.isValidPassword()) {
//            return true;
//        }
//        return false;
        return true;
    }

}
