package com.example.berk.jbanroid;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Category> lstCategory ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstCategory = new ArrayList<>();
        byte[] img = Base64.decode("", Base64.DEFAULT);//VeriTanabınından gelen foto değeri gir
        lstCategory.add(new Category("The Vegitarian","Categorie","Description category", BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategory.add(new Category("The Wild Robot","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategory.add(new Category("Maria Semples","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategory.add(new Category("The Martian","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategory.add(new Category("He Died with...","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategory.add(new Category("The Vegitarian","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategory.add(new Category("The Wild Robot","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategory.add(new Category("Maria Semples","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategory.add(new Category("The Martian","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategory.add(new Category("He Died with...","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this,lstCategory);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);
    }
}
