package com.clowns.foodapp.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.clowns.foodapp.R;

import com.clowns.foodapp.databinding.FragmentMainBinding;
import com.clowns.foodapp.model.CategoryModel;
import com.clowns.foodapp.model.FoodDrinkModel;
import com.clowns.foodapp.viewmodel.adapters.CategoryAdapter;
import com.clowns.foodapp.viewmodel.adapters.FoodDrinkAdapter;

import java.util.ArrayList;
import java.util.List;




public class MainFragment extends Fragment {


    //created by LongLT

    FragmentMainBinding binding;

    List<CategoryModel> categoryList;
    CategoryAdapter categoryAdapter;

    List<FoodDrinkModel> foodDrinkList;
    FoodDrinkAdapter foodDrinkAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create by LongLT
        binding = FragmentMainBinding.inflate(inflater , container, false);
        View view = binding.getRoot();


        binding.profileMainMb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.myProfileFragment);
            }
        });



        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel(R.drawable.welcome, "Category 1"));
        categoryList.add(new CategoryModel(R.drawable.welcome, "Category 2"));
        categoryList.add(new CategoryModel(R.drawable.welcome, "Category 3"));
        categoryList.add(new CategoryModel(R.drawable.welcome, "Category 4"));
        categoryList.add(new CategoryModel(R.drawable.welcome, "Category 5"));
        categoryList.add(new CategoryModel(R.drawable.welcome, "Category 6"));
        categoryList.add(new CategoryModel(R.drawable.welcome, "Category 7"));



        categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
        binding.categoryMainRv.setAdapter(categoryAdapter);
        binding.categoryMainRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.categoryMainRv.setHasFixedSize(true);
        binding.categoryMainRv.setNestedScrollingEnabled(false);


        foodDrinkList = new ArrayList<>();
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 1", "Price 1", "Description 1"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 2", "Price 2", "Description 2"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 3", "Price 3", "Description 3"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 4", "Price 4", "Description 4"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 5", "Price 5", "Description 5"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 6", "Price 6", "Description 6"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 7", "Price 7", "Description 7"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 8", "Price 8", "Description 8"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 9", "Price 9", "Description 9"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 10", "Price 10", "Description 10"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 11", "Price 11", "Description 11"));
        foodDrinkList.add(new FoodDrinkModel(R.drawable.welcome, "Food 12", "Price 12", "Description 12"));


        foodDrinkAdapter = new FoodDrinkAdapter(getContext(), foodDrinkList);


        binding.itemsMainRv.setAdapter(foodDrinkAdapter);
        binding.itemsMainRv.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
        binding.itemsMainRv.setHasFixedSize(true);
        binding.itemsMainRv.setNestedScrollingEnabled(false);

        return view;

    }


}