package com.clowns.foodapp.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.ActivityLoginBinding;
import com.clowns.foodapp.model.fisebase.User;
import com.clowns.foodapp.view.home.MainFragment;
import com.clowns.foodapp.viewmodel.UserViewModel;
import com.clowns.foodapp.viewmodel.adapters.LoginAdapter;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.List;

import javax.annotation.Nullable;

public class LoginActivity extends AppCompatActivity {

    LoginAdapter loginAdapter;
    ActivityLoginBinding activityLoginBinding;

    UserViewModel userViewModel;
    ProgressDialog progressDialog;

    // PhuocDD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginAdapter = new LoginAdapter();
        activityLoginBinding.setLoginAdapter(loginAdapter);
        progressDialog = new ProgressDialog(this);



        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        activityLoginBinding.loginLoginEfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setTitle("Loading ...");
                progressDialog.show();
                userViewModel.getUser(activityLoginBinding.emailPhoneLoginEt.getText().toString()).observe(LoginActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        progressDialog.dismiss();
                        if(loginAdapter.checkLogin(user)){
                            User.getInstance().setUser(user);
                            Intent intent = new Intent(view.getContext(), HomeActivity.class);
                            startActivity(intent);
                        }
                    }
                });
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