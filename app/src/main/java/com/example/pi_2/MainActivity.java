package com.example.pi_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public Activity getActivity(){
        return this;
    }


    public void Go_login(View view){
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
    }


    public void Go_register(View view){
        Intent intent = new Intent(getActivity(), Register.class);
        startActivity(intent);
    }
}
