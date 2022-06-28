package com.clowns.foodapp.viewmodel.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.clowns.foodapp.R;
import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.model.fisebase.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodDrinkAdapter extends RecyclerView.Adapter<FoodDrinkAdapter.ViewHolder> {
    Context context;
    List<FoodDrink> foodDrinkListView;
    List<FoodDrink> foodDrinkListData;

    public FoodDrinkAdapter(Context context, List<FoodDrink> foodDrinkList) {
        this.context = context;
        this.foodDrinkListData = foodDrinkList;
        this.foodDrinkListView = foodDrinkList;
    }

    @NonNull
    @Override
    public FoodDrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_drink_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDrinkAdapter.ViewHolder holder, int position) {
//        holder.imgFoodDrink.setImageResource(foodDrinkList.get(position).getImage());
        holder.tvFoodDrinkName.setText(foodDrinkListView.get(position).getFoodName());
        holder.tvDescriptionFoodDrink.setText(foodDrinkListView.get(position).getDescription());
        holder.fabFoodDrinkFavorite.setImageTintList(ColorStateList.valueOf(Color.parseColor("#F0E1E1")));
        for (FoodDrink foodDrink : User.getInstance().getListFoodDrinkFavorite()) {
            if (foodDrink.getFoodName().equals(foodDrinkListView.get(position).getFoodName())) {
                holder.fabFoodDrinkFavorite.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FE724C")));
            }
        }
        Picasso.get().load(foodDrinkListView.get(position).getUrl()).into(holder.ivFoodDrink);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void filterList(List<FoodDrink> filterList) {
        foodDrinkListView = filterList;
        notifyDataSetChanged();
    }
    public  List<FoodDrink> getFoodDrinkList() {
        return foodDrinkListData;
    }

    @Override
    public int getItemCount() {
        return foodDrinkListView.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvFoodDrink;
        ImageView ivFoodDrink;
        TextView tvFoodDrinkName, tvDescriptionFoodDrink;
        FloatingActionButton fabFoodDrinkFavorite;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvFoodDrink = itemView.findViewById(R.id.food_drink_item_cv);
            ivFoodDrink = itemView.findViewById(R.id.image_food_drink_item_iv);
            tvFoodDrinkName = itemView.findViewById(R.id.food_name_food_drink_item_tv);
            tvDescriptionFoodDrink = itemView.findViewById(R.id.description_food_drink_item_tv);
            fabFoodDrinkFavorite = itemView.findViewById(R.id.favourite_food_drink_item_fab);

            fabFoodDrinkFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (fabFoodDrinkFavorite.getImageTintList() == ColorStateList.valueOf(Color.parseColor("#FE724C"))) {
                        fabFoodDrinkFavorite.setImageTintList(ColorStateList.valueOf(Color.parseColor("#F0E1E1")));
                    } else {
                        fabFoodDrinkFavorite.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FE724C")));
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FoodDrink food = foodDrinkListView.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    boolean isFavorite = fabFoodDrinkFavorite.getImageTintList() ==ColorStateList.valueOf(Color.parseColor("#FE724C"));;
                    bundle.putSerializable("food", food);
                    bundle.putSerializable("isFavorite", isFavorite);
                    Navigation.findNavController(view).navigate(R.id.detailItemFragment, bundle);
                }
            });
        }
    }
}
