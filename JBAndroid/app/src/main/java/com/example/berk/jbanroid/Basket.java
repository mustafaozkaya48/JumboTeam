package com.example.berk.jbanroid;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Basket extends AppCompatActivity {

    ArrayList<BasketModel> basketList;
    RecyclerView recyclerView;
    Database db;
    String[] list;
    JSONArray jsonArray;
    Button Sp_btnSiparis;
    TextView TvBasketNull;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        recyclerView = findViewById(R.id.Bi_rv);
        Sp_btnSiparis=findViewById(R.id.Sp_btnSiparis);
        TvBasketNull=findViewById(R.id.textView2);
        GetBasket();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);
        BasketAdapter adapter = new BasketAdapter(this,basketList);
        recyclerView.setAdapter(adapter);

        Sp_btnSiparis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = list[0]+"/ServiceJB.svc/OrderByTableName";
                try
                {
                    JSONObject BasketValue =  new JSONObject();
                    BasketValue.put("TableName", list[1]);
                    String response = postData(url,BasketValue);
                    Toast.makeText(Basket.this, "Sipariş Verildi. Siparişlerim Bölümünden Takip Edebilirdisiniz.", Toast.LENGTH_SHORT).show();
                    finish();

                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }


    public void GetBasket(){
        db = new Database(getApplicationContext());
        list=db.GetParameter();
        basketList = new ArrayList<>();
        String line=null;
        try {

            // Replace it with your own WCF service path
            URL json = new URL(list[0]+"/ServiceJB.svc/ListBasket/"+list[1]+"");
            URLConnection jc = json.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            line = reader.readLine();

            jsonArray = new JSONArray(line);
            if (jsonArray.length()>0){
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jObject = (JSONObject)jsonArray.get(i);

                    //Toast.makeText(this, ""+jObject.getString("Img"), Toast.LENGTH_SHORT).show();
                    basketList.add(new BasketModel(jObject.getInt("Id"),jObject.getString("ProductName"),jObject.getDouble("ProductPriceId"),jObject.getInt("Count")));
                }

            }
            else{
                Sp_btnSiparis.setEnabled(false);
                TvBasketNull.setVisibility(View.VISIBLE);
            }


            reader.close();

        } catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


    }



    public String postData(String urlpath, JSONObject json)
    {
        HttpURLConnection connection = null;
        try {
            URL url=new URL(urlpath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            OutputStreamWriter streamWriter = new OutputStreamWriter(connection.getOutputStream());
            Log.d("write data",json.toString());
            streamWriter.write(json.toString());
            streamWriter.flush();
            StringBuilder stringBuilder = new StringBuilder();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);
                String response = null;
                while ((response = bufferedReader.readLine()) != null) {
                    stringBuilder.append(response + "\n");
                }
                bufferedReader.close();

                Log.d("HTTP_OK response", stringBuilder.toString());
                return stringBuilder.toString();
            } else {
                Log.e("else response", connection.getResponseMessage());
                return null;
            }
        } catch (Exception exception){
            Log.e("test", exception.toString());
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }

    }
}
