package com.clowns.foodapp.view.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.clowns.foodapp.R;
import com.clowns.foodapp.databinding.ActivityLoginBinding;
import com.clowns.foodapp.view.home.MainFragment;
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

import javax.annotation.Nullable;

public class LoginActivity extends AppCompatActivity {

    LoginAdapter loginAdapter;
    ActivityLoginBinding activityLoginBinding;

    ActivityLoginBinding binding;

    private FirebaseAuth mAuth;
    BeginSignInRequest signInRequest;
    GoogleSignInClient googleSignInClient;
    private final static int REQ_ONE_TAP = 100;
    private boolean showOneTapUI = true;

    // PhuocDD
    @Override
    public void onStart() {
        super.onStart();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // PhuocDD
        mAuth = FirebaseAuth.getInstance();

        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.default_client_id))
                        .setFilterByAuthorizedAccounts(true)
                        .build())
                .build();

        // PhuocDD
        binding.ggLoginLoginMb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this,"TAO LAY MAY",Toast.LENGTH_LONG).show();
                signInWithGoogle();
            }
        });

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

    // PhuocDD
    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityIfNeeded(signInIntent, REQ_ONE_TAP);
    }

    // PhuocDD
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    SignInClient oneTapClient = null;
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    if (idToken !=  null) {
                        // Got an ID token from Google. Use it to authenticate
                        // with Firebase.
                        Toast.makeText(this,"Got ID tonken",Toast.LENGTH_LONG).show();
                    }
                } catch (ApiException e) {
                    switch (e.getStatusCode()) {
                        case CommonStatusCodes.CANCELED:
                            Log.e("Error", "One-tap dialog was closed.");
                            Toast.makeText(this,"ne-tap dialog was closed.",Toast.LENGTH_LONG).show();
                            // Don't re-prompt the user.
                            showOneTapUI = false;
                            break;
                        case CommonStatusCodes.NETWORK_ERROR:
                            Log.e("Error", "One-tap encountered a network error.");
                            Toast.makeText(this,"One-tap encountered a network error.",Toast.LENGTH_LONG).show();
                            // Try again or just ignore.
                            break;
                        default:
                            Log.e("Error", "Couldn't get credential from result."
                                    + e.getLocalizedMessage());
                            Toast.makeText(this,"Couldn't get credential from result.",Toast.LENGTH_LONG).show();
                            break;
                    }
                }
                break;
        }

        SignInClient oneTapClient = null;
        SignInCredential googleCredential = null;
        try {
            googleCredential = oneTapClient.getSignInCredentialFromIntent(data);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        String idToken = googleCredential.getGoogleIdToken();
        if (idToken !=  null) {
            // Got an ID token from Google. Use it to authenticate
            // with Firebase.
            AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
            mAuth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e("Error", "signInWithCredential:success");
                                Toast.makeText(LoginActivity.this,"TAO LAY MAY",Toast.LENGTH_LONG).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("Error", "signInWithCredential:failure", task.getException());
                                Toast.makeText(LoginActivity.this,"TAO LAY MAY",Toast.LENGTH_LONG).show();
                                updateUI(null);
                            }
                        }
                    });
        }
    }

    // PhuocDD
    private void firebaseAuthWithGoogle(GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(getApplicationContext(), MainFragment.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Sign in with Google failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser account){

        if(account != null){
            Toast.makeText(this,"You Signed In successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),MainFragment.class));

        }else {
            Toast.makeText(this,"You Didnt signed in",Toast.LENGTH_LONG).show();
        }

    }
}