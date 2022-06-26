package com.clowns.foodapp.view.others;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.FragmentMyProfileBinding;
import com.clowns.foodapp.model.fisebase.User;
import com.clowns.foodapp.repository.UserRepository;
import com.clowns.foodapp.view.activities.HomeActivity;
import com.clowns.foodapp.view.activities.WelcomeActivity;
import com.clowns.foodapp.viewmodel.CategoryViewModel;
import com.clowns.foodapp.viewmodel.UserViewModel;

public class MyProfileFragment extends Fragment {

    FragmentMyProfileBinding binding;
    User user;
    // Edited by PhatTT
    UserRepository userRepository = new UserRepository();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_my_profile, null, false);
        View viewRoot = binding.getRoot();
        binding.setUser(user);
        return viewRoot;
    }

    // Created by PhatTT
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.signOutEfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), WelcomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        binding.backProfileFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });


        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUser().observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                binding.nameProfileEt.setText(user.getFullName());
            }
        });
    }
}