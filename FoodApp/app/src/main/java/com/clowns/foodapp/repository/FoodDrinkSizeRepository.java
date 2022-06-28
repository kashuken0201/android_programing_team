package com.clowns.foodapp.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.model.fisebase.FoodDrinkSize;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkSizeRepository {
    MutableLiveData<List<FoodDrinkSize>> foodDrinkSizeLiveData;
    FirebaseFirestore db;
    public FoodDrinkSizeRepository(){
        db = FirebaseFirestore.getInstance();
        foodDrinkSizeLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<FoodDrinkSize>> getFoodDrinkSizeList(){
        db.collection("foodSize")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<FoodDrinkSize> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                FoodDrinkSize foodDrinkSize = document.toObject(FoodDrinkSize.class);
                                foodDrinkSize.setSizeId(document.getId());
                                list.add(foodDrinkSize);
                            }
                            foodDrinkSizeLiveData.postValue(list);
                        } else {
                            Log.w("DEBUG", "Error getting documents.", task.getException());
                        }
                    }
                });
        return foodDrinkSizeLiveData;
    }
}
