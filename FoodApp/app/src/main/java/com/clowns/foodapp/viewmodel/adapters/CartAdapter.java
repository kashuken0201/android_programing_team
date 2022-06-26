package com.clowns.foodapp.viewmodel.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clowns.foodapp.R;
import com.clowns.foodapp.model.view.ChoiceItem;
import com.clowns.foodapp.model.view.FoodDrinkCart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<FoodDrinkCart> foodDrinkCartList;

    public CartAdapter(List<FoodDrinkCart> foodDrinkCartList) {
        this.foodDrinkCartList = foodDrinkCartList;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Picasso.get().load(foodDrinkCartList.get(position).getItem().getUrl()).into(holder.image_cart_item_iv);
        holder.food_name_cart_item_tv.setText(foodDrinkCartList.get(position).getItem().getFoodName());
        holder.price_cart_item_tv.setText(String.format("$%s", foodDrinkCartList.get(position).getItem().getPrice()));
        List<ChoiceItem> choice_cart_items = foodDrinkCartList.get(position).getChoiceCartItemList();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < choice_cart_items.size(); i++) {
            stringBuilder.append(choice_cart_items.get(i).getChoiceName());
            if(i != choice_cart_items.size() - 1) stringBuilder.append(", ");
        }
        holder.choices_cart_item_tv.setText(stringBuilder.toString());
        holder.quantity_cart_item_tv.setText(String.valueOf(foodDrinkCartList.get(position).getQuantity()));
    }

    @Override
    public int getItemCount() {
        return foodDrinkCartList.size();
    }

    public float getSubtotal(){
        float subtotal = 0;
        for (int i = 0; i < foodDrinkCartList.size(); i++) {
//            subtotal += foodDrinkCartList.get(i).getPrice();
        }
        return Math.round(subtotal * 100) / 100;
    }

    public float getTotal(){
        return getSubtotal() == 0 ? 0 : getSubtotal() + 2;
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView image_cart_item_iv;
        TextView food_name_cart_item_tv,choices_cart_item_tv,price_cart_item_tv,quantity_cart_item_tv;
        FloatingActionButton up_cart_item_fab,down_cart_item_fab,delete_cart_item_fab;

        @SuppressLint("NotifyDataSetChanged")
        public ViewHolder(@NonNull ViewGroup parent) {
            super(parent);
            image_cart_item_iv = itemView.findViewById(R.id.image_cart_item_iv);
            food_name_cart_item_tv = itemView.findViewById(R.id.food_name_cart_item_tv);
            choices_cart_item_tv = itemView.findViewById(R.id.choices_cart_item_tv);
            price_cart_item_tv = itemView.findViewById(R.id.price_cart_item_tv);
            quantity_cart_item_tv = itemView.findViewById(R.id.quantity_cart_item_tv);
            up_cart_item_fab = itemView.findViewById(R.id.up_cart_item_fab);
            down_cart_item_fab = itemView.findViewById(R.id.down_cart_item_fab);
            delete_cart_item_fab = itemView.findViewById(R.id.delete_cart_item_fab);

            up_cart_item_fab.setOnClickListener(v -> {
                int quantity = Integer.parseInt(quantity_cart_item_tv.getText().toString());
                quantity++;
                quantity_cart_item_tv.setText(String.valueOf(quantity));
                foodDrinkCartList.get(getAdapterPosition()).setQuantity(quantity);
                CartAdapter.this.notifyDataSetChanged();
            });
            down_cart_item_fab.setOnClickListener(v -> {
                int quantity = Integer.parseInt(quantity_cart_item_tv.getText().toString());
                if(quantity > 1) {
                    quantity--;
                    quantity_cart_item_tv.setText(String.valueOf(quantity));
                    foodDrinkCartList.get(getAdapterPosition()).setQuantity(quantity);
                    CartAdapter.this.notifyDataSetChanged();
                }
            });
            delete_cart_item_fab.setOnClickListener(v -> {
                foodDrinkCartList.remove(getAdapterPosition());
                CartAdapter.this.notifyDataSetChanged();
            });
        }
    }
}
