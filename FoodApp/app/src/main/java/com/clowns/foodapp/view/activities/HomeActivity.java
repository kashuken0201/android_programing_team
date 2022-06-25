package com.clowns.foodapp.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.ActivityHomeBinding;
import com.clowns.foodapp.view.cart.MyCartFragment;
import com.clowns.foodapp.view.cart.MyOrdersFragment;
import com.clowns.foodapp.view.home.DetailItemFragment;
import com.clowns.foodapp.view.home.MainFragment;
import com.clowns.foodapp.view.others.MyFavouriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;
    // Edited by PhatTT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.home_page:
                        fragment = new MainFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.favourite_page:
                        fragment = new MyFavouriteFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.cart_page:
                        fragment = new MyCartFragment();
                        loadFragment(fragment);
                        return true;
                    case R.id.order_page:
                        fragment = new MyOrdersFragment();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
    }

    // Created by PhatTT
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragments, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}