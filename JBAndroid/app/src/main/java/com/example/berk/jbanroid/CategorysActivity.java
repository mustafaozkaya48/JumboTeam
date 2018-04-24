package com.example.berk.jbanroid;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class CategorysActivity extends AppCompatActivity {

    List<CategoryModel> lstCategoryModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorys);
        lstCategoryModel = new ArrayList<>();
        byte[] img = Base64.decode("", Base64.DEFAULT);//VeriTanabınından gelen foto değeri gir
        lstCategoryModel.add(new CategoryModel("The Vegitarian","Categorie","Description category", BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategoryModel.add(new CategoryModel("The Wild Robot","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategoryModel.add(new CategoryModel("Maria Semples","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategoryModel.add(new CategoryModel("The Martian","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategoryModel.add(new CategoryModel("He Died with...","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategoryModel.add(new CategoryModel("The Vegitarian","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategoryModel.add(new CategoryModel("The Wild Robot","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategoryModel.add(new CategoryModel("Maria Semples","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategoryModel.add(new CategoryModel("The Martian","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.
        lstCategoryModel.add(new CategoryModel("He Died with...","Categorie","Description category",BitmapFactory.decodeByteArray(img, 0, img.length))); // '0' yazan yere draweble klasörünün içindeki resim gelmesi gerekior.


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        CategoryViewAdapter myAdapter = new CategoryViewAdapter(this, lstCategoryModel);
        myrv.setLayoutManager(new GridLayoutManager(this,3));
        myrv.setAdapter(myAdapter);
    }
}
