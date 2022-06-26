package com.clowns.foodapp.viewmodel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.clowns.foodapp.R;
import com.clowns.foodapp.model.fisebase.Category;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<Category> categoryList;

    public CategoryAdapter(Context context, List<Category> categoryList) {
            this.context = context;
            this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        holder.btnCategory.setIconResource(categoryList.get(position).getCategoryUrl());
        holder.btnCategory.setText(categoryList.get(position).getCategoryName());
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
    }

}
