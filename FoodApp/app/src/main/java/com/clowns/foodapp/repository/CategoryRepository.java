package com.clowns.foodapp.repository;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.clowns.foodapp.model.fisebase.Category;
import com.clowns.foodapp.viewmodel.adapters.CategoryAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    MutableLiveData<List<Category>> categoryLiveDataList;
    FirebaseFirestore db;
    public CategoryRepository(){
        db = FirebaseFirestore.getInstance();
        categoryLiveDataList = new MutableLiveData<>();
    }

    public MutableLiveData<List<Category>> getCategoryList(){
        db.collection("category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<Category> list = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category category = document.toObject(Category.class);
                                category.setCategoryId(document.getId());
                                list.add(category);
                            }
                            categoryLiveDataList.postValue(list);
                        } else {
                            Log.w("DEBUG", "Error getting documents.", task.getException());
                        }
                    }
                });
        return categoryLiveDataList;
    }
}
