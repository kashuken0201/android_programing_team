package com.clowns.foodapp.viewmodel.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clowns.foodapp.R;
import com.clowns.foodapp.model.FoodDrinkModel;

import java.util.List;

public class FoodDrinkAdapter extends RecyclerView.Adapter<FoodDrinkAdapter.ViewHolder> {
    Context context;
    List<FoodDrinkModel> foodDrinkList;

    public FoodDrinkAdapter(Context context, List<FoodDrinkModel> foodDrinkList) {
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
        holder.imgFoodDrink.setImageResource(foodDrinkList.get(position).getImage());
        holder.txtNameFoodDrink.setText(foodDrinkList.get(position).getName());
        holder.txtDescriptionFoodDrink.setText(foodDrinkList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return foodDrinkList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFoodDrink;
        TextView txtNameFoodDrink, txtDescriptionFoodDrink;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoodDrink = itemView.findViewById(R.id.image_food_drink_item_iv);
            txtNameFoodDrink = itemView.findViewById(R.id.food_name_food_drink_item_tv);
            txtDescriptionFoodDrink = itemView.findViewById(R.id.description_food_drink_item_tv);

        }
    }
}
