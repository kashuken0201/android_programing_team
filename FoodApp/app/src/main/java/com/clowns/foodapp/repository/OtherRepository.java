package com.clowns.foodapp.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.model.fisebase.Other;
import com.clowns.foodapp.model.view.ChoiceItem;
import com.clowns.foodapp.viewmodel.adapters.ChoiceItemAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OtherRepository {
    MutableLiveData<List<ChoiceItem>> choiceItemListLiveData;
    FirebaseFirestore db;
    public OtherRepository(){
        db = FirebaseFirestore.getInstance();
        choiceItemListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<ChoiceItem>> getChoiceItemList(){
        db.collection("other")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<ChoiceItem> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Other other = document.toObject(Other.class);
                                ChoiceItem choiceItem = new ChoiceItem(other.getOtherName(), other.getPrice(), other.getUrl());
                                list.add(choiceItem);
                            }
                            choiceItemListLiveData.postValue(list);
                        } else {
                            Log.w("DEBUG", "Error getting documents.", task.getException());
                        }
                    }
                });
        return choiceItemListLiveData;
    }
}
