package com.clowns.foodapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.repository.FoodDrinkRepository;

import java.util.List;

public class FoodDrinkViewModel extends ViewModel {
    MutableLiveData<List<FoodDrink>> foodDrinkLiveData;
    FoodDrinkRepository foodDrinkRepository;

    public FoodDrinkViewModel() {
        foodDrinkRepository = new FoodDrinkRepository();
        foodDrinkLiveData = foodDrinkRepository.getFoodDrinkList();
    }

    public MutableLiveData<List<FoodDrink>> getLiveFoodDrinkData() {
        return foodDrinkLiveData;
    }
}
