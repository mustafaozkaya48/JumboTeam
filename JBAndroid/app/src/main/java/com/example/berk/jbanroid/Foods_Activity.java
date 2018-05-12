package com.example.berk.jbanroid;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Foods_Activity extends AppCompatActivity {
    Bundle extars;
    RecyclerView recyclerView;
    Database db;
    String[] list;
    ArrayList<FoodModel> foodsList;
    ImageView categoryImage;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods_);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        categoryImage=findViewById(R.id.CategoryImg);
        recyclerView = findViewById(R.id.rv);
        db = new Database(getApplicationContext());
        list=db.GetParameter();

        extars=getIntent().getExtras();
        GetCategoryimg();
        GetProducts();


    }
    private void GetCategoryimg() {
        String line=null;
        try {URL json = new URL(list[0]+"/ServiceJB.svc/GetCategoryImage/"+extars.getString("Title")+"");
            URLConnection jc = json.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            line = reader.readLine();
            if (line!=""){
                Bitmap bmp=null;
                URL url = new URL(list[0]+"/"+line.substring(1,line.length()-1));
                bmp   = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                categoryImage.setImageBitmap(bmp);
            }
            reader.close();
        } catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void GetProducts(){
        foodsList = new ArrayList<>();
        String line=null;
        try {

            URL json = new URL(list[0]+"/ServiceJB.svc/GetProductListByCategoryName/"+extars.getString("Title")+"");
            URLConnection jc = json.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            line = reader.readLine();
            jsonArray = new JSONArray(line);
            for (int i =0 ; i < jsonArray.length(); i++){
                JSONObject jo= (JSONObject)jsonArray.get(i);
                Bitmap bmp=null;

                if (jo.getString("Img")!=""){
                    URL url = new URL(list[0]+"/"+jo.getString("Img"));
                    bmp   = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }

             foodsList.add(new FoodModel(bmp,jo.getString("ProductName"),jo.getString("ProductAbout"),jo.getString("Id")));

            }
            reader.close();


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            RecyclerView.LayoutManager rvLiLayoutManager = linearLayoutManager;
            recyclerView.setLayoutManager(rvLiLayoutManager);
            FoodAdapter adapter = new FoodAdapter(this,foodsList);
            recyclerView.setAdapter(adapter);

        } catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }



}
