package com.clowns.foodapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.clowns.foodapp.model.fisebase.Category;
import com.clowns.foodapp.repository.CategoryRepository;

import java.util.List;

public class CategoryViewModel extends ViewModel {
    MutableLiveData<List<Category>> categoryLiveDataList;
    CategoryRepository categoryRepository;

    public CategoryViewModel() {
        categoryRepository = new CategoryRepository();
        categoryLiveDataList=  categoryRepository.getCategoryList();
    }

    public MutableLiveData<List<Category>> getLiveCategoryData() {
        return categoryLiveDataList;
    }
}
