package com.example.berk.jbanroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Basket extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }


}
