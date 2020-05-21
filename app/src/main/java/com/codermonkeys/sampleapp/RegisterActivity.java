package com.codermonkeys.sampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RegisterActivity extends AppCompatActivity {

    //var's
    public static Boolean setSignUpFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        if(setSignUpFragment) {
       // setSignUpFragment = false;
//            setDefaultFragment(new SignUpFragment);
//        } else {
//            setDefaultFragment(new SignInFragment);
//        }
    }
}
