package com.example.berk.jbanroid;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class add_basket extends AppCompatActivity {

    Button Arttir,Azalt,Cikis,SepeteEkle;
    Bundle extars;
    EditText Sayisi;
    TextView UrunAdi,UrunAciklama;
    Spinner Porsiyon;
    Database db;
    String[] list;
    JSONArray jsonArray;
    String Porsiyons[]=null;
    int _SelectedPorsionId=0;
    int Ids[] =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_basket);
        db = new Database(getApplicationContext());
        list=db.GetParameter();
        extars=getIntent().getExtras();
        UrunAdi =findViewById(R.id.tvProductName);
        UrunAciklama=findViewById(R.id.TvpAbout);
        UrunAdi.setText(extars.getString("ProductName"));
        UrunAciklama.setText(extars.getString("ProductAbout"));
        ButtonAyarları(this);
        Porsiyonlar(this);
        Toast.makeText(this, ""+_SelectedPorsionId, Toast.LENGTH_SHORT).show();
    }

   public void Porsiyonlar(add_basket view){

       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);
       String line=null;
       try {

           // Replace it with your own WCF service path
           URL json = new URL(list[0]+"/ServiceJB.svc/GetListPorsionByProductId/"+extars.getInt("ProductId")+"");
           URLConnection jc = json.openConnection();
           BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
           line = reader.readLine();

           jsonArray = new JSONArray(line);
           Porsiyons=new String[jsonArray.length()];
           Ids= new int[jsonArray.length()];
           for (int i = 0; i < jsonArray.length(); i++)
           {
               JSONObject jObject = (JSONObject)jsonArray.get(i);
               Porsiyons[i]=""+jObject.getString("ProductUnit")+" -- "+jObject.getString("Price")+"TL";
               Ids[i]=jObject.getInt("Id");
           }

           reader.close();

       } catch(Exception e){
           Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
       }
       Porsiyon=findViewById(R.id.spinnerPosiyon);
       ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Porsiyons);
       spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       Porsiyon.setAdapter(spinnerArrayAdapter);

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
           Porsiyon.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
       }
       Porsiyon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
              _SelectedPorsionId=Ids[i];

           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });


   }
   public  void ButtonAyarları(add_basket view){
            SepeteEkle=findViewById(R.id.buttonEkle);
         SepeteEkle.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String url = list[0]+"/ServiceJB.svc/AddBasket";
                 try
                 {
                     JSONObject BasketValue =  new JSONObject();
                     BasketValue.put("Count",Sayisi.getText() );
                     BasketValue.put("ProductId",extars.getInt("ProductId") );
                     BasketValue.put("ProductName",UrunAdi.getText() );
                     BasketValue.put("ProductPriceId",_SelectedPorsionId);//Product Price Id
                     BasketValue.put("Table", list[1]);
                     String response = postData(url,BasketValue);
                     Toast.makeText(add_basket.this, "Sepete Eklendi.", Toast.LENGTH_SHORT).show();
                     finish();

                 }
                 catch(Exception ex)
                 {
                     ex.printStackTrace();
                 }

             }
         });
        Cikis=findViewById(R.id.buttonExit);
        Sayisi=findViewById(R.id.editTextSayi);
        Arttir=findViewById(R.id.buttonArttir);
        Azalt=findViewById(R.id.Bi_buttonAzalt);
        Cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Arttir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(Sayisi.getText().toString())<20){
                    int Sayi=Integer.parseInt(Sayisi.getText().toString())+1;
                    Sayisi.setText(""+Sayi);
                }
            }
        });
        Azalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Integer.parseInt(Sayisi.getText().toString())>1){
                    int Sayi=Integer.parseInt(Sayisi.getText().toString())-1;
                    Sayisi.setText(""+Sayi);
                }
            }
        });

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
