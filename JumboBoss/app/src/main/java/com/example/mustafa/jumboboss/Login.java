package com.example.mustafa.jumboboss;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class Login extends Activity {
    Button btnGiris;
Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

     btnGiris=findViewById(R.id.btnGiris);


    }

    public void Giris(View view){

        //if (){  veritabanındaki değerle aynıysa

        Intent intent = new Intent(this,Menu.class);
        startActivity(intent);


        //}


    }
}
