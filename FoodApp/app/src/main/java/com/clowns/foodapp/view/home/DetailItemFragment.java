package com.clowns.foodapp.view.home;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.FragmentDetailItemBinding;
import com.clowns.foodapp.model.Food;
import com.clowns.foodapp.viewmodel.adapters.ChoiceItemAdapter;
//Edited by PVK
public class DetailItemFragment extends Fragment {
//    no model
    private Food food;
    private int quantity=1;
    // test favorite
    private boolean f=true;
    private FragmentDetailItemBinding binding;
    private ChoiceItemAdapter choiceItemAdapter;

    // test function
    void addTestData(){
        food= new Food("com ga","ngu",1000000,"123","hahaha");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // no model
//            food=(Food) getArguments().getSerializable("food");
        }
        // add test data
        addTestData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailItemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        load();
    }

    private void load(){
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
                // no model
//                bundle.putSerializable("Food",food);
//                bundle.putSerializable("quantity",quantity);

                Fragment fragment = new MainFragment();
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
                if(f==true){
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
        choiceItemAdapter=new ChoiceItemAdapter();
        binding.choicesDetailItemRv.setAdapter(choiceItemAdapter);
        binding.choicesDetailItemRv.setLayoutManager(new LinearLayoutManager(getContext()));
    }
    private void setDetailFood(){
        binding.setFood(food);
        binding.quantityDetailItemTv.setText(String.valueOf(quantity));

    }
    private void setFavorite(){

    }
}