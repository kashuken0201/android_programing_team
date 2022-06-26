package com.clowns.foodapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.clowns.foodapp.model.fisebase.User;
import com.clowns.foodapp.repository.UserRepository;

public class UserViewModel extends ViewModel {
    MutableLiveData<User> userLiveData;
    UserRepository userRepository;

    public UserViewModel(){
        userRepository = new UserRepository();
        userLiveData = userRepository.getUser();
    }

    public MutableLiveData<User> getUser(){
        return userLiveData;
    }
}
