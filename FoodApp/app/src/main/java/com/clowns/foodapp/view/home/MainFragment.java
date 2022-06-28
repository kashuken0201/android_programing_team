package com.clowns.foodapp.view.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clowns.foodapp.R;

import com.clowns.foodapp.databinding.FragmentMainBinding;
import com.clowns.foodapp.model.fisebase.Category;
import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.repository.FoodDrinkRepository;
import com.clowns.foodapp.viewmodel.CategoryViewModel;
import com.clowns.foodapp.viewmodel.FoodDrinkViewModel;
import com.clowns.foodapp.viewmodel.adapters.CategoryAdapter;
import com.clowns.foodapp.viewmodel.adapters.FoodDrinkAdapter;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    //created by LongLT
    FragmentMainBinding binding;

    CategoryAdapter categoryAdapter;
    FoodDrinkAdapter foodDrinkAdapter;
    FoodDrinkViewModel foodDrinkViewModel;
    CategoryViewModel categoryViewModel;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void filterName(String text) {
        ArrayList<FoodDrink> filteredList = new ArrayList<>();
        for (FoodDrink item : foodDrinkAdapter.getFoodDrinkList()) {
            String name = item.getFoodName();
            if (name.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        if (!filteredList.isEmpty()) {
            foodDrinkAdapter.filterList(filteredList);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //create by LongLT
        binding = FragmentMainBinding.inflate(inflater , container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressDialog = new ProgressDialog(getContext());
        loadFoodDrink();
        loadCategory();

        binding.searchMainEt.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterName(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        binding.profileMainMb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.myProfileFragment);
            }
        });

    }

    private void loadCategory(){
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        progressDialog.setTitle("Loading ...");
        progressDialog.show();

        categoryViewModel.getLiveCategoryData().observe(getViewLifecycleOwner(), new Observer<List<Category>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Category> categoryList) {
                progressDialog.dismiss();
                categoryAdapter = new CategoryAdapter(getContext(), categoryList, foodDrinkAdapter);
                binding.categoryMainRv.setAdapter(categoryAdapter);
                categoryAdapter.notifyDataSetChanged();
            }
        });
        binding.categoryMainRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.categoryMainRv.setHasFixedSize(true);
        binding.categoryMainRv.setNestedScrollingEnabled(false);
    }
    private void loadFoodDrink(){
        foodDrinkViewModel = new ViewModelProvider(this).get(FoodDrinkViewModel.class);
        progressDialog.setTitle("Loading ...");
        progressDialog.show();

        foodDrinkViewModel.getLiveFoodDrinkData().observe(getViewLifecycleOwner(), new Observer<List<FoodDrink>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<FoodDrink> foodDrinkList) {
                progressDialog.dismiss();
                foodDrinkAdapter = new FoodDrinkAdapter(getContext(), foodDrinkList);
                binding.itemsMainRv.setAdapter(foodDrinkAdapter);
                foodDrinkAdapter.notifyDataSetChanged();
            }
        });
        binding.itemsMainRv.setLayoutManager(new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false));
        binding.itemsMainRv.setHasFixedSize(true);
        binding.itemsMainRv.setNestedScrollingEnabled(false);
    }

}