package com.example.berk.jbanroid;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private Context mContext;

    Database db;
    String[] dblist;
    private ArrayList<BasketModel> mList;
    BasketAdapter(Context context, ArrayList<BasketModel> list){
        mContext = context;
        mList = list;



        db = new Database(context);
        dblist=db.GetParameter();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.basket_items,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final BasketModel BasketItem = mList.get(position);
        final TextView UrunAdi,Sayisi,Fiyati;
        final Button btnArt,btnAzalt,btnSil;

        UrunAdi = holder.Adi;
        Sayisi = holder.sayisi;
        Fiyati=holder.tutar;
        btnSil=holder.btnsil;
        btnArt = holder.btnart;
        btnAzalt=holder.btnazalt;
        UrunAdi.setText(BasketItem.getProductName());
        Sayisi.setText(""+BasketItem.getCount());
        btnArt.setId(BasketItem.getId());
        btnAzalt.setId(BasketItem.getId());
        btnSil.setId(BasketItem.getId());
        double snc=BasketItem.getProductPrice()*BasketItem.getCount();
        Fiyati.setText(""+snc);
        final DecimalFormat twoPlaces = new DecimalFormat("0.00");
        btnAzalt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (parseInt(Sayisi.getText().toString())>1){
                    String url =dblist[0]+"/ServiceJB.svc/BasketCountStateChange";
                    try {
                        JSONObject jsonObject= new JSONObject();
                        jsonObject.put("Count",-1);
                        jsonObject.put("Id",btnAzalt.getId());
                        postData(url,jsonObject);
                    }catch (Exception ex){

                    }
                    Double ToplamFiyat=Double.parseDouble(Fiyati.getText().toString())-Double.parseDouble(Fiyati.getText().toString())/Double.parseDouble(Sayisi.getText().toString());
                    Sayisi.setText(String.valueOf(parseInt(Sayisi.getText().toString())-1));
                    Fiyati.setText(twoPlaces.format(ToplamFiyat));

                }

            }
        });
         btnArt.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (parseInt(Sayisi.getText().toString())<20){
                String url =dblist[0]+"/ServiceJB.svc/BasketCountStateChange";
                try {

                    JSONObject jsonObject= new JSONObject();
                    jsonObject.put("Count",1);
                    jsonObject.put("Id",btnAzalt.getId());
                    postData(url,jsonObject);
                }catch (Exception ex){

                }

                Double ToplamFiyat=Double.parseDouble(Fiyati.getText().toString())+Double.parseDouble(Fiyati.getText().toString())/Double.parseDouble(Sayisi.getText().toString());


                Fiyati.setText(""+twoPlaces.format(ToplamFiyat));

                Sayisi.setText(String.valueOf(parseInt(Sayisi.getText().toString())+1));
            }



        }});


        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mList.size());
                holder.itemView.setVisibility(View.GONE);
                JSONObject JsonObject= new JSONObject();
                String url =dblist[0]+"/ServiceJB.svc/BasketDeleteItem";
                try {
                    JSONObject jsonObject= new JSONObject();
                    jsonObject.put("Id",btnSil.getId());
                    postData(url,jsonObject);
                }catch (Exception ex){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView Adi,sayisi,tutar;
        Button btnsil,btnart,btnazalt;
        public ViewHolder(View itemView) {
            super(itemView);
            Adi = itemView.findViewById(R.id.Bi_tvUrunAdi);
            sayisi = itemView.findViewById(R.id.Bi_tvSayi);
            tutar = itemView.findViewById(R.id.Bi_tvTutar);
            btnsil = itemView.findViewById(R.id.Bi_btnSil);
            btnart=itemView.findViewById(R.id.Bi_buttonArttir);
            btnazalt=itemView.findViewById(R.id.Bi_buttonAzalt);
        }
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
