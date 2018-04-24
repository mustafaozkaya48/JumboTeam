package com.example.berk.jbanroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Foods_Activity extends AppCompatActivity {

    /*private TextView tvtitle,tvdescription,tvcategory;
    private ImageView img;*/

    RecyclerView recyclerView;


    ArrayList<FoodModel> foodsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_);

        recyclerView = findViewById(R.id.rv);

        foodsList = new ArrayList<>();

        foodsList.add(new FoodModel(R.drawable.maxresdefault, "biryani","Karachi Savour","4"));
        foodsList.add(new FoodModel(R.drawable.maxresdefault, "haleem","Chaman Savour","6"));
        foodsList.add(new FoodModel(R.drawable.maxresdefault, "chaat","Karachi Foods","3"));
        foodsList.add(new FoodModel(R.drawable.maxresdefault, "kare","Karachi Savour","7"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);

        FoodAdapter adapter = new FoodAdapter(this,foodsList);

        recyclerView.setAdapter(adapter);



       /* tvtitle = (TextView) findViewById(R.id.txttitle);
        tvdescription = (TextView) findViewById(R.id.txtDesc);
        tvcategory = (TextView) findViewById(R.id.txtCat);
        img=(ImageView) findViewById(R.id.categorythumbnail);
        //Recieve Data

        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");*/



        //settings values

       /* tvtitle.setText(Title);*/


        

    }
}
