package com.clowns.foodapp.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.viewmodel.adapters.FoodDrinkAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkRepository {
    MutableLiveData<List<FoodDrink>> foodDrinkListLiveData;
    FirebaseFirestore db;

    public FoodDrinkRepository(){
        this.db = FirebaseFirestore.getInstance();
        this.foodDrinkListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<FoodDrink>> getFoodDrinkList(){
        db.collection("food")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<FoodDrink> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                FoodDrink foodDrink = document.toObject(FoodDrink.class);
                                foodDrink.setId(document.getId());
                                list.add(foodDrink);
                            }
                            foodDrinkListLiveData.postValue(list);
                        } else {
                            Log.w("DEBUG", "Error getting documents.", task.getException());
                        }
                    }
                });
        return foodDrinkListLiveData;
    }
}
