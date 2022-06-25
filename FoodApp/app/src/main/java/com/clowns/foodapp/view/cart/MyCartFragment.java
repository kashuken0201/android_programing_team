package com.clowns.foodapp.view.cart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.FragmentMyCartBinding;
import com.clowns.foodapp.model.ChoiseCartItem;
import com.clowns.foodapp.model.Food;
import com.clowns.foodapp.model.FoodCart;
import com.clowns.foodapp.viewmodel.adapters.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {

    List<FoodCart> foodCartList;
    CartAdapter cartAdapter;

    FragmentMyCartBinding binding;


    public MyCartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_cart, container, false);
        binding = FragmentMyCartBinding.bind(view);

        binding.cartsMyCartRv.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        foodCartList = new ArrayList<>();
//
//        List<ChoiseCartItem> choiseCartItemList = new ArrayList<>();
//        choiseCartItemList.add(new ChoiseCartItem("Pho mai", 3.99f));
//        choiseCartItemList.add(new ChoiseCartItem("Nam", 1.99f));
//
//        Food food1 = new Food("Pizza ga", 9.99f, "https://s23209.pcdn.co/wp-content/uploads/2021/10/BBQ-Chicken-PizzaIMG_0027.jpg");
//        Food food2 = new Food("Hamburger", 9.99f,"https://hamburgerdanang.com/wp-content/uploads/2021/03/the-ultimate-hamburger.jpg");
//        Food food3 = new Food("Pizza dac biet", 9.99f,"https://hanamihotel.com/wp-content/uploads/2019/12/Pizza-4P%E2%80%99s-%C4%90%C3%A0-N%E1%BA%B5ng-2.jpg");
//
//        foodCartList.add(new FoodCart(food1,1, choiseCartItemList));
//        foodCartList.add(new FoodCart(food2,3, new ArrayList<>()));
//        foodCartList.add(new FoodCart(food3,2, choiseCartItemList));
//        foodCartList.add(new FoodCart(food1,1, choiseCartItemList));
//        foodCartList.add(new FoodCart(food2,3, new ArrayList<>()));
//        foodCartList.add(new FoodCart(food3,2, choiseCartItemList));
//        foodCartList.add(new FoodCart(food1,1, choiseCartItemList));
//        foodCartList.add(new FoodCart(food2,3, new ArrayList<>()));
//        foodCartList.add(new FoodCart(food3,2, choiseCartItemList));
//
//        cartAdapter = new CartAdapter(foodCartList);
//        binding.cartsMyCartRv.setAdapter(cartAdapter);
//
//        cartAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onChanged() {
//                super.onChanged();
//                binding.subtotalPriceMyCartTv.setText("$"+String.valueOf(cartAdapter.getSubtotal()));
//                binding.totalPriceMyCartTv.setText("$"+String.valueOf(cartAdapter.getTotal()));
//            }
//        });
//
//        binding.subtotalPriceMyCartTv.setText("$"+String.valueOf(cartAdapter.getSubtotal()));
//        binding.totalPriceMyCartTv.setText("$"+String.valueOf(cartAdapter.getTotal()));
        return view;
    }

}