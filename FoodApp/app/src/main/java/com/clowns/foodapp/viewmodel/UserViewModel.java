package com.clowns.foodapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.clowns.foodapp.model.fisebase.User;
import com.clowns.foodapp.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    MutableLiveData<User> userLiveData;
    UserRepository userRepository;

    public UserViewModel(){
        userRepository = new UserRepository();

    }

    public MutableLiveData<User> getUser(String email){
        userLiveData = userRepository.getUser(email);
        return userLiveData;
    }
    public MutableLiveData<User> addUser(User user){
        userLiveData = userRepository.addUser(user);
        return userLiveData;
    }
}
