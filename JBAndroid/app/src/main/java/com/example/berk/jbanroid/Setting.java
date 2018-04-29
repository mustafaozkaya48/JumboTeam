package com.example.berk.jbanroid;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

        public class Setting extends AppCompatActivity {
            Spinner spinner;
            Button btnSave;
            EditText etConString;
            Database db;
            String[] list;
            String Tables[];
            JSONArray jsonArray;
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_setting);







                db = new Database(getApplicationContext());
        list=db.GetParameter();
        etConString=findViewById(R.id.setting_et_ConString);
        btnSave = findViewById(R.id.buttonSave);
        spinner =findViewById(R.id.spinner);
        etConString.setText(list[0]);
        TablesAdaptor();
        int indexOf = ArrayUtils.indexOf(Tables, list[1]);
        Button btnChange =findViewById(R.id.BtnChange);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etConString.setEnabled(true);
            }
        });

        SaveChange(this);
       spinner.setSelection(indexOf);


    }
    public void TablesAdaptor(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String line=null;
        try {

            // Replace it with your own WCF service path
            URL json = new URL(list[0]+"/ServiceJB.svc/GetListTableNames");
            URLConnection jc = json.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(jc.getInputStream()));
            line = reader.readLine();

            jsonArray = new JSONArray(line);
            Tables=new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jObject = (JSONObject)jsonArray.get(i);
                if (jObject.getString("TableName")!="null")Tables[i]=jObject.getString("TableName");

            }

            reader.close();

        } catch(Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        Toast.makeText(this, ""+ Arrays.toString(Tables), Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_dropdown_item, Tables);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
    }
    public void SaveChange(Setting view){
      btnSave.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              db.resetTables();
              db.AddParameter(etConString.getText().toString());
              Toast.makeText(Setting.this, ""+spinner.getSelectedItem(), Toast.LENGTH_SHORT).show();
              db.AddParameter(spinner.getSelectedItem().toString());


          }
      });
    }









}
