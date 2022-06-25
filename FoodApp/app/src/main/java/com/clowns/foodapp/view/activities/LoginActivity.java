package com.clowns.foodapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.ActivityLoginBinding;
import com.clowns.foodapp.viewmodel.adapters.LoginAdapter;

public class LoginActivity extends AppCompatActivity {

    LoginAdapter loginAdapter;
    ActivityLoginBinding activityLoginBinding;
    // Created by PhatTT
    // Edited by PhatTT
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Edited by NhanLT
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginAdapter = new LoginAdapter();
        activityLoginBinding.setLoginAdapter(loginAdapter);

        activityLoginBinding.loginLoginEfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(loginAdapter.checkLogin()){
                    Intent intent = new Intent(view.getContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        activityLoginBinding.linkRegisterLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}