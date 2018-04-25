package com.example.berk.jbanroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

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
                          Intent setting = new Intent(getApplicationContext(),SettingsActivity.class);
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
