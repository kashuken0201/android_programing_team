package com.clowns.foodapp.viewmodel.adapters;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.clowns.foodapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

// created by KhaiPV
public class ChoiceItemAdapter extends RecyclerView.Adapter<ChoiceItemAdapter.ViewHolder>  {
    // no model
    ArrayList<ChoiceItem> choiceItemList;

        // add test data
    void addTestData(){
        choiceItemList.add(new ChoiceItem("rice",12.5,"123"));
        choiceItemList.add(new ChoiceItem("noodle",13.5,"123"));
        choiceItemList.add(new ChoiceItem("hehe",15.5,"123"));
    }

    public ChoiceItemAdapter() {
        // no model
        choiceItemList = new ArrayList<>();
        addTestData();
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
        ChoiceItem item= choiceItemList.get(position);
        holder.choiceItemName.setText(item.getName());
        holder.choiceItemPrice.setText(String.valueOf(item.getPrice()));
        if(item.isSelected()==true){
            holder.choiceItemFab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FE724C")));
        }
        else{
            holder.choiceItemFab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#F0E1E1")));
        }
        holder.choiceItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item.isSelected()==true){
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

        // no model
        return choiceItemList.size();
//        return 0;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView choiceItemImage;
        TextView choiceItemName;
        TextView choiceItemPrice;
        FloatingActionButton choiceItemFab;
        public ViewHolder(View view) {
            super(view);
            choiceItemImage=view.findViewById(R.id.image_choice_item_iv);
            choiceItemName=view.findViewById(R.id.name_choice_item_tv);
            choiceItemPrice=view.findViewById(R.id.price_choice_item_tv);
            choiceItemFab=view.findViewById(R.id.choice_item_fab);

        }
    }



    class ChoiceItem{
        private String name;
        private double price;
        private String image;
        private boolean selected;

        public ChoiceItem(String name, double price, String image) {
            this.name = name;
            this.price = price;
            this.image = image;
            this.selected = false;
        }

        public String getName() {
            return name;
        }

        public void setSelected(boolean selected) {
            this.selected=selected;
        }

        public double getPrice() {
            return price;
        }

        public String getImage() {
            return image;
        }

        public boolean isSelected() {
            return selected;
        }

    }

}
