package com.clowns.foodapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.ActivityRegistrationBinding;
import com.clowns.foodapp.view.home.MainFragment;
import com.clowns.foodapp.viewmodel.UserViewModel;
import com.clowns.foodapp.viewmodel.adapters.LoginAdapter;
import com.clowns.foodapp.viewmodel.adapters.RegisterAdapter;

public class RegistrationActivity extends AppCompatActivity {

    RegisterAdapter registerAdapter;
    ActivityRegistrationBinding activityRegistrationBinding;
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //Edited by Nhan
        activityRegistrationBinding = DataBindingUtil.setContentView(this,R.layout.activity_registration);
        registerAdapter = new RegisterAdapter();
        activityRegistrationBinding.setRegisterAdapter(registerAdapter);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        activityRegistrationBinding.linkLoginRegisterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        activityRegistrationBinding.signupRegisterEfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DEBUG", String.valueOf(registerAdapter.checkRegister()));
                if (registerAdapter.checkRegister()){
                    userViewModel.addUser(registerAdapter.getUser());
                    Intent intent = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}