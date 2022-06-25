package com.clowns.foodapp.viewmodel.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clowns.foodapp.R;
import com.clowns.foodapp.model.ChoiseCartItem;
import com.clowns.foodapp.model.FoodCart;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;


import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    List<FoodCart> foodCartList;

    public CartAdapter(List<FoodCart> foodCartList) {
        this.foodCartList = foodCartList;
    }

    public CartAdapter() {
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder((ViewGroup) LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        Picasso.get().load(foodCartList.get(position).getFood().getUrlImage()).into(holder.image_cart_item_iv);
        holder.food_name_cart_item_tv.setText(foodCartList.get(position).getFood().getName());
        holder.price_cart_item_tv.setText("$"+ foodCartList.get(position).getFood().getPrice());
        List<ChoiseCartItem> choise_cart_items = foodCartList.get(position).getChoiseCartItemList();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < choise_cart_items.size(); i++) {
            stringBuilder.append(choise_cart_items.get(i).getName());
            if(i != choise_cart_items.size() - 1) stringBuilder.append(", ");
        }
        holder.choices_cart_item_tv.setText(stringBuilder.toString());
        holder.quantity_cart_item_tv.setText(String.valueOf(foodCartList.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return foodCartList.size();
    }

    public float getSubtotal(){
        float subtotal = 0;
        for (int i = 0; i < foodCartList.size(); i++) {
            subtotal += foodCartList.get(i).getPrice();
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
                foodCartList.get(getAdapterPosition()).setAmount(quantity);
                CartAdapter.this.notifyDataSetChanged();
            });
            down_cart_item_fab.setOnClickListener(v -> {
                int quantity = Integer.parseInt(quantity_cart_item_tv.getText().toString());
                if(quantity > 1) {
                    quantity--;
                    quantity_cart_item_tv.setText(String.valueOf(quantity));
                    foodCartList.get(getAdapterPosition()).setAmount(quantity);
                    CartAdapter.this.notifyDataSetChanged();
                }
            });
            delete_cart_item_fab.setOnClickListener(v -> {
                foodCartList.remove(getAdapterPosition());
                CartAdapter.this.notifyDataSetChanged();
            });
        }
    }
}
