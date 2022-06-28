package com.clowns.foodapp.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.model.fisebase.FoodDrinkSize;
import com.clowns.foodapp.model.fisebase.Other;
import com.clowns.foodapp.model.fisebase.User;
import com.clowns.foodapp.model.view.ChoiceItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    MutableLiveData<List<User>> listUserLiveData;
    MutableLiveData<User> userLiveData;
    FirebaseFirestore db;

    public UserRepository() {
        db = FirebaseFirestore.getInstance();
        listUserLiveData = new MutableLiveData<>();
        userLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<User> getUser(String email) {
        db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<User> users = new ArrayList<>();
                            List<Task<DocumentSnapshot>> tasks_reference_fooddrink = new ArrayList<>();
                            List<Task<DocumentSnapshot>> tasks_reference_fooddrinksize = new ArrayList<>();
                            List<Task<DocumentSnapshot>> tasks_reference_other = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                User user = document.toObject(User.class);
                                user.setUserId(document.getId());
                                for (String keyitem : user.getCart().keySet()) {
                                    HashMap<String, Object> map = (HashMap<String, Object>) user.getCart().get(keyitem);
                                    for (String key : map.keySet()) {
                                        if (key.equals("food")) {
                                            // get food from document reference
                                            DocumentReference foodRef = (DocumentReference) map.get(key);
                                            Task<DocumentSnapshot> task_food = foodRef.get();
                                            tasks_reference_fooddrink.add(task_food);
                                        }
                                        if (key.equals("foodsize")) {
                                            // get food size from document reference
                                            DocumentReference foodSizeRef = (DocumentReference) map.get(key);
                                            Task<DocumentSnapshot> task_reference = foodSizeRef.get();
                                            tasks_reference_fooddrinksize.add(task_reference);
                                        }
                                        if (key.equals("quantity")) {

                                            // get quantity from document reference
                                            ((HashMap<String, Object>) (user.getCart().get(keyitem))).put("quantity", Integer.valueOf(map.get(key).toString()));
                                        }
                                        if (key.equals("choiceItemList")) {
                                            HashMap<String, Object> choiseItemList = (HashMap<String, Object>) map.get(key);
                                            for (String keychoiseItem : choiseItemList.keySet()) {
                                                DocumentReference choiseItemRef = (DocumentReference) choiseItemList.get(keychoiseItem);
                                                Task<DocumentSnapshot> task_reference_choiseItem = choiseItemRef.get();
                                                tasks_reference_other.add(task_reference_choiseItem);
                                            }
                                        }
                                    }
                                }
                                //wait for all food and food size to be loaded
                                users.add(user);
                            }
                            if (users.size() > 0) {
                                User user = users.get(0);
                                Tasks.whenAllSuccess(tasks_reference_fooddrink).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
                                    @Override
                                    public void onSuccess(List<Object> objects) {
                                        int i = 0;
                                        for (String keyitem : user.getCart().keySet()) {
                                            HashMap<String, Object> map = (HashMap<String, Object>) user.getCart().get(keyitem);
                                            for (String key : map.keySet()) {
                                                if (key.equals("food")) {
                                                    FoodDrink foodDrink = ((DocumentSnapshot) objects.get(i)).toObject(FoodDrink.class);
                                                    ((HashMap<String, Object>) (user.getCart().get(keyitem))).put("food", foodDrink);
                                                    foodDrink.setId(((DocumentSnapshot) objects.get(i)).getId());
                                                    ((HashMap<String, Object>) user.getCart().get(keyitem)).put(key, foodDrink);
                                                    i++;
                                                }
                                            }
                                        }
                                        userLiveData.setValue(user);
                                    }
                                });
                                Tasks.whenAllSuccess(tasks_reference_fooddrinksize).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
                                    @Override
                                    public void onSuccess(List<Object> objects) {
                                        int i = 0;
                                        for (String keyitem : user.getCart().keySet()) {
                                            HashMap<String, Object> map = (HashMap<String, Object>) user.getCart().get(keyitem);
                                            for (String key : map.keySet()) {
                                                if (key.equals("foodsize")) {
                                                    FoodDrinkSize foodDrinkSize = ((DocumentSnapshot) objects.get(i)).toObject(FoodDrinkSize.class);
                                                    foodDrinkSize.setSizeId(((DocumentSnapshot) objects.get(i)).getId());
                                                    ((HashMap<String, Object>) user.getCart().get(keyitem)).put(key, foodDrinkSize);
                                                    i++;
                                                }
                                            }
                                        }
                                        userLiveData.setValue(user);
                                    }
                                });
                                Tasks.whenAllSuccess(tasks_reference_other).addOnSuccessListener(new OnSuccessListener<List<Object>>() {
                                    @Override
                                    public void onSuccess(List<Object> objects) {
                                        int i = 0;
                                        for (String keyitem : user.getCart().keySet()) {
                                            HashMap<String, Object> map = (HashMap<String, Object>) user.getCart().get(keyitem);
                                            for (String key : map.keySet()) {
                                                if (key.equals("choiceItemList")) {
                                                    HashMap<String, Object> choiseItemList = (HashMap<String, Object>) map.get(key);
                                                    for (String keychoiseItem : choiseItemList.keySet()) {
                                                        Other other = ((DocumentSnapshot) objects.get(i)).toObject(Other.class);
                                                        other.setOtherId(((DocumentSnapshot) objects.get(i)).getId());
                                                        ChoiceItem choiceItem = new ChoiceItem(other.getOtherId(), other.getOtherName(), other.getPrice(), other.getUrl());
                                                        ((HashMap<String, Object>) ((HashMap<String, Object>) user.getCart().get(keyitem)).get(key)).put(keychoiseItem, choiceItem);
                                                        i++;
                                                    }
                                                }
                                            }
                                        }
                                        userLiveData.setValue(user);
                                    }
                                });
                            }
                        } else {
                            Log.w("DEBUG", "Error getting documents.", task.getException());
                        }
                    }
                });
        return userLiveData;
    }

    public MutableLiveData<User> addUser(User user) {
        if (getUser(user.getEmail()).getValue() != null) {
            return userLiveData;
        }
        Map<String, Object> obj = new HashMap<>();
        obj.put("email", user.getEmail());
        obj.put("password", user.getPassword());
        obj.put("fullName", user.getFullName());
        obj.put("cart", user.getCart());
        obj.put("favourite", user.getFavourite());
        Log.d("DEBUG", obj.toString());
        db.collection("users").add(obj);
        return userLiveData;
    }
}
