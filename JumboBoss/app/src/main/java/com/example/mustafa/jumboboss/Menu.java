package com.example.mustafa.jumboboss;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import  android.widget.*;

import static android.view.View.*;

public class Menu extends Activity {

    LinearLayout l;
    ImageButton newButton;
    LayoutParams lp;
    Context context;


    Intent intentBasket = new Intent(getApplicationContext(),Basket.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu:

                        return true;
                    case R.id.navigation_basket:
                   startActivity(intentBasket);

                          return true;
                    case R.id.navigation_orders:

                        return true;
                }
                return false;
            }
        });




        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();



        l=findViewById(R.id.linearLayoutScrool);
        int j=0;
        int colmn=(width/200)-1;
        int İtemCount=10;
        int bolkalan=20%3;

        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        for(int i=0;i<İtemCount;i++){
            j++;
            if (j<=colmn){
                LinearLayout llitem= new LinearLayout(this);
                llitem.setOrientation(LinearLayout.VERTICAL);
                llitem.setBackgroundColor(Color.LTGRAY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(220,220);

                layoutParams.setMargins(10,10,10,10);

                llitem.setLayoutParams(layoutParams);
                TextView txt = new TextView(this);
                txt.setTextColor(Color.WHITE);
                txt.setText("Deneme "+i);
                txt.setGravity(Gravity.CENTER);
                txt.setTextSize(22);
                newButton = new ImageButton(this);


                newButton.setLayoutParams(new LinearLayout.LayoutParams(220,180));
                newButton.setImageResource(R.drawable.corba);
                newButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                newButton.setId(i);
                final int id=newButton.getId();
                newButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getApplicationContext(), FoodSelect.class);
                        int mesaj = id;
                        intent.putExtra("CategoryId", mesaj);
                        startActivity(intent);

                    }
                });

                llitem.addView(txt);
                llitem.addView(newButton);
                linearLayout.addView(llitem);
            }
            if (j==colmn){
                j=0;
                l.addView(linearLayout);
                linearLayout= new LinearLayout(this);
            }
            if (i==İtemCount-1 && İtemCount%colmn!=0){
                l.addView(linearLayout);
            }


        }













        /*int j=0;
        int colmn=4;
        int size=20;
        int bolkalan=20%3;*/
       /* LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        for(int i=0;i<size;i++){
            j++;
          if (j<=colmn){
               newButton = new Button(this);
               newButton.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
               linearLayout.addView(newButton);
           }
            if (j==colmn){
               j=0;
               l.addView(linearLayout);
               linearLayout= new LinearLayout(this);
           }


        }*/





        //servisten masa sayısını getirecek ona göre scroll view da buton oluşturacak. Butonlar oluşturulurken  bir satırda en fazla 5-6 buton olabilir gibi bişey yapmak lazım.
    }




    public void cikis(View view){

        Intent intent = new Intent(this,Login.class);
        startActivity(intent);

    }
}
