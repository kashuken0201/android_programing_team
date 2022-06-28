package com.clowns.foodapp.viewmodel.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.clowns.foodapp.R;
import com.clowns.foodapp.model.fisebase.Category;
import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<Category> categoryList;
    FoodDrinkAdapter foodDrinkAdapter;

    public CategoryAdapter(Context context, List<Category> categoryList, FoodDrinkAdapter foodDrinkList) {
            this.context = context;
            this.categoryList = categoryList;
            this.foodDrinkAdapter = foodDrinkList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.btnCategory.setIconResource(categoryList.get(position).getCategoryUrl());
        holder.btnCategory.setText(categoryList.get(position).getCategoryName());
        holder.btnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FoodDrink> foodDrinkList = new ArrayList<>();
                Log.d("TAG", "onClick: " + foodDrinkAdapter.toString());
                if (foodDrinkAdapter == null) return;
                for (FoodDrink foodDrink : foodDrinkAdapter.getFoodDrinkList()) {
                    if (foodDrink.getCategoryName().equals(categoryList.get(position).getCategoryName())) {
                        foodDrinkList.add(foodDrink);
                    }
                }
                foodDrinkAdapter.filterList(foodDrinkList);
                foodDrinkAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        MaterialButton btnCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnCategory = itemView.findViewById(R.id.category_item_mb);
        }
        public  MaterialButton getBtnCategory() {
            return btnCategory;
        }
    }

}
