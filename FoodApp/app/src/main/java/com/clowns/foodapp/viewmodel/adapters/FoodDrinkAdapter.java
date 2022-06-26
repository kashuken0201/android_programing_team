package com.clowns.foodapp.viewmodel.adapters;

import android.content.Context;
import android.os.Bundle;
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

import java.util.List;

public class FoodDrinkAdapter extends RecyclerView.Adapter<FoodDrinkAdapter.ViewHolder> {
    Context context;
    List<FoodDrink> foodDrinkList;

    public FoodDrinkAdapter(Context context, List<FoodDrink> foodDrinkList) {
        this.context = context;
        this.foodDrinkList = foodDrinkList;
    }

    @NonNull
    @Override
    public FoodDrinkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.food_drink_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDrinkAdapter.ViewHolder holder, int position) {
//        holder.imgFoodDrink.setImageResource(foodDrinkList.get(position).getImage());
        holder.tvFoodDrinkName.setText(foodDrinkList.get(position).getFoodName());
        holder.tvDescriptionFoodDrink.setText(foodDrinkList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return foodDrinkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvFoodDrink;
        ImageView ivFoodDrink;
        TextView tvFoodDrinkName, tvDescriptionFoodDrink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvFoodDrink = itemView.findViewById(R.id.food_drink_item_cv);
            ivFoodDrink = itemView.findViewById(R.id.image_food_drink_item_iv);
            tvFoodDrinkName = itemView.findViewById(R.id.food_name_food_drink_item_tv);
            tvDescriptionFoodDrink = itemView.findViewById(R.id.description_food_drink_item_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FoodDrink food = foodDrinkList.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("food", food);
                    Navigation.findNavController(view).navigate(R.id.detailItemFragment, bundle);
                }
            });
        }
    }
}
