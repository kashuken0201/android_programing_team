package com.clowns.foodapp.viewmodel.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.clowns.foodapp.R;
import com.clowns.foodapp.model.view.ChoiceItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

// Created by KhaiPV
public class ChoiceItemAdapter extends RecyclerView.Adapter<ChoiceItemAdapter.ViewHolder>  {

    Context context;
    List<ChoiceItem> choiceItemList;

    public ChoiceItemAdapter(Context context, List<ChoiceItem> choiceItemList) {
        this.context = context;
        this.choiceItemList = choiceItemList;
    }

    @NonNull
    @Override
    public ChoiceItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.choice_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoiceItemAdapter.ViewHolder holder, int position) {
        // no model
        ChoiceItem item = choiceItemList.get(position);
        holder.choiceItemName.setText(item.getChoiceName());
        holder.choiceItemPrice.setText(String.valueOf(item.getChoicePrice()));
        if(item.isSelected() == true){
            holder.choiceItemFab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FE724C")));
        }
        else{
            holder.choiceItemFab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#F0E1E1")));
        }
        holder.choiceItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isSelected() == true){
                    item.setSelected(false);
                    holder.choiceItemFab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#F0E1E1")));

                }
                else {
                    item.setSelected(true);
                    holder.choiceItemFab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FE724C")));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return choiceItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView choiceItemImage;
        TextView choiceItemName;
        TextView choiceItemPrice;
        FloatingActionButton choiceItemFab;
        public ViewHolder(View view) {
            super(view);
            choiceItemImage = view.findViewById(R.id.image_choice_item_iv);
            choiceItemName = view.findViewById(R.id.name_choice_item_tv);
            choiceItemPrice = view.findViewById(R.id.price_choice_item_tv);
            choiceItemFab = view.findViewById(R.id.choice_item_fab);
        }
    }
}
