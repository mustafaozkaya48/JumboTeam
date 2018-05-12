package com.example.berk.jbanroid;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class Orders extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<OrderModel> orderList;
    Database db;
    String[] list;
    JSONArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        orderList = new ArrayList<>();
        recyclerView = findViewById(R.id.Oi_rv);
        GetOrders();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager rvLiLayoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(rvLiLayoutManager);
        OrdersAdapter adapter = new OrdersAdapter(this,orderList);
        recyclerView.setAdapter(adapter);
    }


    public void GetOrders(){
        db = new Database(getApplicationContext());
        list=db.GetParameter();
        orderList = new ArrayList<>();
        String line=null;
        try {

            // Replace it with your own WCF service path
            URL json = new URL(list[0]+"/ServiceJB.svc/GetListOrdersByTableName/"+list[1]+"");
            URLConnection jc = json.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            line = reader.readLine();

            jsonArray = new JSONArray(line);

           // Toast.makeText(this, ""+line, Toast.LENGTH_SHORT).show();
            if (jsonArray.length()>0){
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jObject = (JSONObject)jsonArray.get(i);
                    //Toast.makeText(this, ""+jObject.getString("Img"), Toast.LENGTH_SHORT).show();
                    orderList.add(new OrderModel(jObject.getInt("Id"),jObject.getString("ProductName"),jObject.getDouble("Price"),jObject.getInt("Quantitiy"),jObject.getString("Porsion"),jObject.getString("State")));
                }
            }
            else{
                 TextView textView =findViewById(R.id.Oi_SiparisYok);
                 textView.setVisibility(View.VISIBLE);
            }


            reader.close();

        } catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }


    }

}
