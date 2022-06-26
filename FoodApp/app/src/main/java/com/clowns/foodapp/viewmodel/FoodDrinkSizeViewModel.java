package com.clowns.foodapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.clowns.foodapp.model.fisebase.FoodDrinkSize;
import com.clowns.foodapp.model.fisebase.User;
import com.clowns.foodapp.repository.FoodDrinkRepository;
import com.clowns.foodapp.repository.FoodDrinkSizeRepository;
import com.clowns.foodapp.repository.UserRepository;

import java.util.List;

public class FoodDrinkSizeViewModel extends ViewModel {
    MutableLiveData<List<FoodDrinkSize>> foodDrinkSizeListLiveData;
    FoodDrinkSizeRepository foodDrinkSizeRepository;

    public FoodDrinkSizeViewModel(){
        foodDrinkSizeRepository = new FoodDrinkSizeRepository();
        foodDrinkSizeListLiveData = foodDrinkSizeRepository.getFoodDrinkSizeList();
    }

    public MutableLiveData<List<FoodDrinkSize>> getFoodDrinkSizeList(){
        return foodDrinkSizeListLiveData;
    }
}
