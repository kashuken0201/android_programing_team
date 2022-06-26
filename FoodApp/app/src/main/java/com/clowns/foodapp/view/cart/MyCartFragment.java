package com.clowns.foodapp.view.cart;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.clowns.foodapp.viewmodel.adapters.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    List<FoodDrinkCart> foodDrinkCartList;
    CartAdapter cartAdapter;
    FragmentMyCartBinding binding;
    FoodDrinkCart fdc;
    UserRepository userRepository;
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.cartsMyCartRv.setLayoutManager(new LinearLayoutManager(getContext()));

        foodDrinkCartList = new ArrayList<>();
        List<ChoiceItem> choiceItemList = new ArrayList<>();
        choiceItemList.add(new ChoiceItem("Pho mai", 3.99, ""));
        choiceItemList.add(new ChoiceItem("Nam", 1.99, ""));

        FoodDrink food1 = new FoodDrink("Ga", "Rice", 9.99, "", "https://s23209.pcdn.co/wp-content/uploads/2021/10/BBQ-Chicken-PizzaIMG_0027.jpg");
        FoodDrink food2 = new FoodDrink("Ga", "Noodle", 9.99, "", "https://s23209.pcdn.co/wp-content/uploads/2021/10/BBQ-Chicken-PizzaIMG_0027.jpg");
        FoodDrink food3 = new FoodDrink("Coca Cola", "Drink", 9.99, "", "https://s23209.pcdn.co/wp-content/uploads/2021/10/BBQ-Chicken-PizzaIMG_0027.jpg");

        FoodDrinkSize fs1 = new FoodDrinkSize("Small", 1.0);
        FoodDrinkSize fs2 = new FoodDrinkSize("Medium", 1.0);
        FoodDrinkSize fs3 = new FoodDrinkSize("Big", 1.0);

        foodDrinkCartList.add(new FoodDrinkCart(food1 , fs1, 1, choiceItemList));
        foodDrinkCartList.add(new FoodDrinkCart(food2 , fs2,3, new ArrayList<>()));
        foodDrinkCartList.add(new FoodDrinkCart(food3 , fs1,2, choiceItemList));
        foodDrinkCartList.add(new FoodDrinkCart(food1 , fs3, 1, choiceItemList));

        cartAdapter = new CartAdapter(foodDrinkCartList);
        binding.cartsMyCartRv.setAdapter(cartAdapter);

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

    private void loadMyCart(){

    }
}