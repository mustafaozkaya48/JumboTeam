package com.example.berk.jbanroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class CategorysActivity extends AppCompatActivity {

    List<CategoryModel> lstCategoryModel;
    Database db;
    String[] list;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorys);
        BaglantiAyarlar(this);
        AltMenu(this);

    }
    public void AltMenu(CategorysActivity view){
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu:
                        return true;
                    case R.id.navigation_basket:
                        Intent intentBasket = new Intent(getApplicationContext(),Basket.class);
                        startActivity(intentBasket);
                        return true;
                    case R.id.navigation_orders:
                        Intent intentOrders = new Intent(getApplicationContext(),Orders.class);
                        startActivity(intentOrders);
                        return true;
                }
                return false;
            }
        });
    }
    public void BaglantiAyarlar(CategorysActivity view){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        db = new Database(getApplicationContext());
        list=db.GetParameter();
        lstCategoryModel =FromJSONtoArrayList();
        RecyclerView myrv = (RecyclerView)findViewById(R.id.recyclerview_id);
        CategoryViewAdapter myAdapter = new CategoryViewAdapter(this, lstCategoryModel);
        myrv.setLayoutManager(new GridLayoutManager(this,4));
        myrv.setAdapter(myAdapter);



    }
    public List<CategoryModel> FromJSONtoArrayList() {
        lstCategoryModel= new ArrayList<>();
        String line=null;
        try {

            // Replace it with your own WCF service path
            URL json = new URL(list[0]+"/ServiceJB.svc/GetListCategory");
            URLConnection jc = json.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            line = reader.readLine();
            jsonArray = new JSONArray(line);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject = (JSONObject)jsonArray.get(i);
                Bitmap bmp=null;

                if (jObject.getString("Img")!=""){
                    URL url = new URL(list[0]+"/"+jObject.getString("Img"));
                    bmp   = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                }




               //Toast.makeText(this, ""+jObject.getString("Img"), Toast.LENGTH_SHORT).show();
                 lstCategoryModel.add(new CategoryModel(jObject.getString("CategoryName"),jObject.getString("CategoryName"),jObject.getString("CategoryName"),bmp));
            }

            reader.close();

        } catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }

        return lstCategoryModel;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.setting, menu);
        return true;
    }
    String m_Text = "";
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Yetkili Şifresini Giriniz");

                final EditText input = new EditText(this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_Text = input.getText().toString();
                        if (m_Text.equals("01")){
                          Intent setting = new Intent(getApplicationContext(),Setting.class);
                          startActivity(setting);
                        }
                        else   Toast.makeText(CategorysActivity.this, "Yanlış", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

                return true;
                 default:
                return super.onOptionsItemSelected(item);
        }
    }

}
