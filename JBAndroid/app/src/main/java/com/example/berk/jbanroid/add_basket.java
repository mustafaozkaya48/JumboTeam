package com.example.berk.jbanroid;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class add_basket extends AppCompatActivity {

    Button Arttir,Azalt,Cikis,SepeteEkle;
    EditText Sayisi;
    TextView UrunAdi,UrunAciklama;
    Spinner Porsiyon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_basket);
        ButtonAyarları(this);
        Porsiyonlar(this);
    }

   public void Porsiyonlar(add_basket view){
   String Porsiyonlar[] = {"1 Porsiyon 15 TL","1.5 Porsiyon 20 TL"};

       Porsiyon=findViewById(R.id.spinnerPosiyon);
       ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Porsiyonlar);
       spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       Porsiyon.setAdapter(spinnerArrayAdapter);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Porsiyon.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }



   }
        public  void ButtonAyarları(add_basket view){
            SepeteEkle=findViewById(R.id.buttonEkle);
         SepeteEkle.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });
        Cikis=findViewById(R.id.buttonExit);
        Sayisi=findViewById(R.id.editTextSayi);
        Arttir=findViewById(R.id.buttonArttir);
        Azalt=findViewById(R.id.buttonAzalt);
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

}
