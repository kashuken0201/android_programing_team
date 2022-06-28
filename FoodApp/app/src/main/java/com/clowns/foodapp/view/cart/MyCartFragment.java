package com.clowns.foodapp.view.cart;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.FragmentMyCartBinding;
import com.clowns.foodapp.model.view.ChoiceItem;
import com.clowns.foodapp.model.fisebase.FoodDrink;
import com.clowns.foodapp.model.view.FoodDrinkCart;
import com.clowns.foodapp.model.fisebase.FoodDrinkSize;
import com.clowns.foodapp.repository.UserRepository;
import com.clowns.foodapp.view.home.MainFragment;
import com.clowns.foodapp.viewmodel.ChoiceItemViewModel;
import com.clowns.foodapp.viewmodel.FoodDrinkSizeViewModel;
import com.clowns.foodapp.viewmodel.FoodDrinkViewModel;
import com.clowns.foodapp.viewmodel.UserViewModel;
import com.clowns.foodapp.viewmodel.adapters.CartAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import androidx.lifecycle.Observer;

import com.clowns.foodapp.model.fisebase.User;
import com.clowns.foodapp.repository.UserRepository;

public class MyCartFragment extends Fragment {

    List<FoodDrinkCart> foodDrinkCartList;
    CartAdapter cartAdapter;
    FragmentMyCartBinding binding;
    FoodDrinkCart fdc;
    UserViewModel userViewModel;
    FoodDrinkSizeViewModel foodDrinkSizeViewModel;
    FoodDrinkViewModel foodDrinkViewModel;
    ChoiceItemViewModel choiceItemViewModel;

    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fdc = (FoodDrinkCart) getArguments().getSerializable("cart");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        binding = FragmentMyCartBinding.bind(view);
        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        foodDrinkSizeViewModel = new ViewModelProvider(this).get(FoodDrinkSizeViewModel.class);
        foodDrinkViewModel = new ViewModelProvider(this).get(FoodDrinkViewModel.class);
        choiceItemViewModel = new ViewModelProvider(this).get(ChoiceItemViewModel.class);
        binding.backMyCartFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new MainFragment());

            }
        });

        binding.cartsMyCartRv.setLayoutManager(new LinearLayoutManager(getContext()));

        foodDrinkCartList = new ArrayList<>();

        cartAdapter = new CartAdapter(foodDrinkCartList);
        binding.cartsMyCartRv.setAdapter(cartAdapter);
        progressDialog.setTitle("Loading ...");
        progressDialog.show();
        userViewModel.getUser(User.getInstance().getEmail()).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                List<FoodDrinkCart> foodDrinkCartList1 = user.getListFoodDrinkCart();
                if(foodDrinkCartList1 == null) return;
                progressDialog.dismiss();
                foodDrinkCartList.clear();
                foodDrinkCartList.addAll(foodDrinkCartList1);
                cartAdapter.notifyDataSetChanged();
            }
        });

        cartAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                binding.subtotalPriceMyCartTv.setText(String.format("$%s", cartAdapter.getSubtotal()));
                binding.totalPriceMyCartTv.setText(String.format("$%s", cartAdapter.getTotal()));
            }
        });

        binding.subtotalPriceMyCartTv.setText(String.format("$%s", cartAdapter.getSubtotal()));
        binding.totalPriceMyCartTv.setText(String.format("$%s", cartAdapter.getTotal()));
    }

    private void loadMyCart() {
    }

    private FoodDrink getCartFromDatabase(String cart[]) {
        FoodDrink food1 = new FoodDrink(cart[0], "Rice", Double.parseDouble(cart[1]), cart[2], "https://s23209.pcdn.co/wp-content/uploads/2021/10/BBQ-Chicken-PizzaIMG_0027.jpg");
        return food1;
    }

}