package com.clowns.foodapp.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.clowns.foodapp.model.fisebase.User;
import com.clowns.foodapp.model.view.FoodDrinkCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    MutableLiveData<User> userLiveData;
    FirebaseFirestore db;
    public UserRepository(){
        db = FirebaseFirestore.getInstance();
        userLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<User> getUser(){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            User user = new User();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                user = document.toObject(User.class);
                            }
                            userLiveData.postValue(user);
                        } else {
                            Log.w("DEBUG", "Error getting documents.", task.getException());
                        }
                    }
                });
        return userLiveData;
    }
}
