package com.clowns.foodapp.view.others;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


import com.clowns.foodapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MyProfileFragment extends Fragment {
    //created by LongLT
    FloatingActionButton btnBackHome;
    EditText txtName,txtEmailPhone,txtPassword;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        btnBackHome = view.findViewById(R.id.back_profile_fab);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_myProfileFragment_to_mainFragment);

            }
        });
        MaterialButton btnProfile = inflater.inflate(R.layout.fragment_main, container, false).findViewById(R.id.profile_main_mb);

        txtName = view.findViewById(R.id.name_profile_et);
        txtName.setText("LongLT");
        txtEmailPhone = view.findViewById(R.id.email_phone_profile_et);
        txtEmailPhone.setText("LongLT");
        txtPassword = view.findViewById(R.id.password_profile_et);
        txtPassword.setText("123456");
        return view;
    }
}