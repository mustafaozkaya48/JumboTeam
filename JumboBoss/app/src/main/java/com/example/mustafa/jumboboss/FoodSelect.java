package com.example.mustafa.jumboboss;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


public class FoodSelect extends Activity  {
    Context context;
    ImageButton imageButton;
    LinearLayout l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_food_select);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
       /* navigation.setOnNavigationItemSelectedListener(Helper.mOnNavigationItemSelectedListener);*/


        Bundle extras = getIntent().getExtras();
        int value = extras.getInt("CategoryId");

        Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();


        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        l=findViewById(R.id.llscrool);
        int j=0;
        int colmn=(width/200)-1;
        int İtemCount=10;
        int bolkalan=20%3;

        LinearLayout linearLayout=new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        for(int i=0;i<İtemCount;i++){
            j++;
            if (j<=colmn){
                LinearLayout llitem= new LinearLayout(this);
                llitem.setOrientation(LinearLayout.VERTICAL);
                llitem.setBackgroundColor(Color.LTGRAY);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(220,250);

                layoutParams.setMargins(10,10,10,10);

                llitem.setLayoutParams(layoutParams);
                TextView txt = new TextView(this);
                txt.setTextColor(Color.WHITE);
                txt.setText("Deneme "+i);
                txt.setGravity(Gravity.CENTER);
                txt.setTextSize(22);
                imageButton = new ImageButton(this);

                imageButton.setLayoutParams(new LinearLayout.LayoutParams(220,180));
                imageButton.setImageResource(R.drawable.corba);
                imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageButton.setId(i);
                final int id=imageButton.getId();
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       /* Intent intent = new Intent(getApplicationContext(), FoodSelect.class);
                        int mesaj = id;
                        intent.putExtra("CategoryId", mesaj);
                        startActivity(intent);*/

                    }
                });

                Button btn = new Button(this);
                btn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                btn.setText("sepete ekle");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        EditText count = new EditText(FoodSelect.this);

                        count.setInputType(InputType.TYPE_CLASS_NUMBER);

                        AlertDialog.Builder builder = new AlertDialog.Builder(FoodSelect.this);
                        builder.setTitle("Kaç adet");
                        builder.setNegativeButton("Ekle", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                                //
                                Toast.makeText(FoodSelect.this, "Eklendi", Toast.LENGTH_SHORT).show();
                            }

                        });
                        builder.setPositiveButton("İptal", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i)
                            {

                            }

                        });
                        builder.setView(count);
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                });

                llitem.addView(txt);
                llitem.addView(imageButton);
                llitem.addView(btn);
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

    }




}
