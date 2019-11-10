package com.example.pi_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pi_2.R;

public class MyOffers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_offers);
        getSupportActionBar().hide();
    }
}
