package com.example.berk.jbanroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Category_Activity extends AppCompatActivity {

    private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_);



        tvtitle = (TextView) findViewById(R.id.txttitle);
        tvdescription = (TextView) findViewById(R.id.txtDesc);
        tvcategory = (TextView) findViewById(R.id.txtCat);
        img=(ImageView) findViewById(R.id.categorythumbnail);
        //Recieve Data

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description= intent.getExtras().getString("Description");
        int image =  intent.getExtras().getInt("Thumbnail");


        //settings values

        tvtitle.setText(Title);
        tvdescription.setText(Description);
        img.setImageResource(image);

        

    }
}
