package com.clowns.foodapp.viewmodel.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;


import com.clowns.foodapp.BR;
import com.clowns.foodapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class LoginAdapter extends BaseObservable {
    private String email;
    private String password;
    public ArrayList<User> listUser= getListUsers();
    public ObservableField<String> message= new ObservableField<>("Login");

    @Bindable
    public String getEmail() {
        return email;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    public ArrayList<User> getListUsers(){
//        ArrayList<User> listUser = new ArrayList<>();
//        listUser.add(new User("nhan@gmail.com","12345678"));
//        return listUser;
        return null;
    }

    public boolean checkLogin(){
//        User user = new User(getEmail(),getPassword());
//        if(user.isValidEmail() && user.isValidPassword()) {
//            for(int i=0;i<listUser.size();i++) {
//                if (user.getUsername().equals(listUser.get(i).getUsername())
//                        && user.getPassword().equals(listUser.get(i).getPassword())) {
//                    message.set("Succes");
//                    return true;
//
//                }
//            }
//        }
//        else {
//            message.set("Failed");
//            return false;
//        }
//        return  false;
        return true;
    }

}

//    public void addUser(User user){
//        Map<String, Object> obj = new HashMap<>();
//        obj.put("email", user.getUsername());
//        obj.put("password", user.getPassword());
//        obj.put("phoneNumber", " ");
//        obj.put("uid", " ");
//        obj.put("username", " ");
//
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("users")
//                .add(obj)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d("DEBUG", "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w("DEBUG", "Error adding document", e);
//                    }
//                });
//    }

