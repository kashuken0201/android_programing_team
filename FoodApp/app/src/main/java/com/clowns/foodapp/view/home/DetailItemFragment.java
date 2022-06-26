package com.clowns.foodapp.view.home;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.FragmentDetailItemBinding;
import com.clowns.foodapp.model.fisebase.FoodDrinkSize;
import com.clowns.foodapp.model.fisebase.Other;
import com.clowns.foodapp.model.view.ChoiceItem;
import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.model.view.FoodDrinkCart;
import com.clowns.foodapp.repository.OtherRepository;
import com.clowns.foodapp.view.cart.MyCartFragment;
import com.clowns.foodapp.viewmodel.ChoiceItemViewModel;
import com.clowns.foodapp.viewmodel.FoodDrinkViewModel;
import com.clowns.foodapp.viewmodel.adapters.CategoryAdapter;
import com.clowns.foodapp.viewmodel.adapters.ChoiceItemAdapter;
import com.clowns.foodapp.viewmodel.adapters.FoodDrinkAdapter;

import java.util.ArrayList;
import java.util.List;

//Edited by PVK
public class DetailItemFragment extends Fragment {
//    no model
    private FoodDrink food;
    private int quantity=1;
    // test favorite
    private boolean f=true;
    private FragmentDetailItemBinding binding;
    private ChoiceItemAdapter choiceItemAdapter;
    private ArrayList<ChoiceItem> choiceItemList;
    private ChoiceItemViewModel choiceItemViewModel;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            food = (FoodDrink) getArguments().getSerializable("food");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailItemBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        load();
    }

    private void load(){
        progressDialog = new ProgressDialog(getContext());
        setView();
        setEvent();
    }

    private void setEvent(){
        setAddToCartFab();
        setQuantityFab();
        setFavoriteFab();
        setBackFab();
    }

    private void setView(){
        setChoiceItemView();
        setDetailFood();
    }


    private void setAddToCartFab(){
        binding.addCartDetailItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                FoodDrinkCart fdc = new FoodDrinkCart(
                        food,
                        new FoodDrinkSize(),
                        quantity,
                        choiceItemList
                );
                bundle.putSerializable("cart", fdc);

                Fragment fragment = new MyCartFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragments, fragment);
                fragmentTransaction.commit();
            }
        });
    }
    private void setBackFab(){
        binding.backDetailItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
    private void setQuantityFab(){
        binding.upDetailItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity+=1;
                binding.quantityDetailItemTv.setText(String.valueOf(quantity));
            }
        });
        binding.downDetailItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity-=1;
                if(quantity<1) quantity=1;
                binding.quantityDetailItemTv.setText(String.valueOf(quantity));
            }
        });
    }
    private void setFavoriteFab(){
        binding.favouriteDetailItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(f == true){
                    f=false;
                    binding.favouriteDetailItemFab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#F0E1E1")));
                }
                else{
                    f=true;
                    binding.favouriteDetailItemFab.setImageTintList(ColorStateList.valueOf(Color.parseColor("#FE724C")));
                }
            }
        });
    }

    private void setChoiceItemView(){
        choiceItemViewModel = new ViewModelProvider(this).get(ChoiceItemViewModel.class);
        progressDialog.setTitle("Loading ...");
        progressDialog.show();

        choiceItemViewModel.getLiveChoiceItemData().observe(getViewLifecycleOwner(), new Observer<List<ChoiceItem>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<ChoiceItem> choiceItemList) {
                progressDialog.dismiss();
                choiceItemAdapter = new ChoiceItemAdapter(getContext(), choiceItemList);
                binding.choicesDetailItemRv.setAdapter(choiceItemAdapter);
                choiceItemAdapter.notifyDataSetChanged();
            }
        });

        binding.choicesDetailItemRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setDetailFood(){
        binding.setFood(food);
        binding.quantityDetailItemTv.setText(String.valueOf(quantity));
    }
    private void setFavorite(){

    }
}