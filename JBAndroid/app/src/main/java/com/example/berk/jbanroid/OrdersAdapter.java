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
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;


public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {

    private Context mContext;

    Database db;
    String[] dblist;
    private ArrayList<OrderModel> mList;
    OrdersAdapter(Context context, ArrayList<OrderModel> list){
        mContext = context;
        mList = list;
        db = new Database(context);
        dblist=db.GetParameter();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        View view = layoutInflater.inflate(R.layout.order_items,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final OrderModel OrderItem = mList.get(position);
        final TextView UrunAdi,Sayisi,Fiyati,Durumu;
        final Button btnSil;

        UrunAdi = holder.Adi;
        Sayisi = holder.sayisi;
        Fiyati=holder.tutar;
        Durumu=holder.Durumu;
        btnSil=holder.btnsil;

        UrunAdi.setText(OrderItem.getProductName()+" - ("+OrderItem.getPorsion()+")");
        Sayisi.setText(""+OrderItem.getCount());
        Durumu.setText(OrderItem.getState());
        btnSil.setId(OrderItem.getId());
        double snc=OrderItem.getProductPrice()*OrderItem.getCount();
        Fiyati.setText(""+snc);
        final DecimalFormat twoPlaces = new DecimalFormat("0.00");
        btnSil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mList.size());
                holder.itemView.setVisibility(View.GONE);
                JSONObject JsonObject= new JSONObject();
                String url =dblist[0]+"/ServiceJB.svc/OrderDeleteItem";
                try {
                    JSONObject jsonObject= new JSONObject();
                    jsonObject.put("Id",btnSil.getId());
                    //Toast.makeText(mContext, ""+btnSil.getId(), Toast.LENGTH_SHORT).show();
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

        TextView Adi,sayisi,tutar,Durumu;
        Button btnsil;
        public ViewHolder(View itemView) {
            super(itemView);
            Adi = itemView.findViewById(R.id.Oi_tvUrunAdi);
            sayisi = itemView.findViewById(R.id.Oi_tvSayi);
            tutar = itemView.findViewById(R.id.Oi_tvTutar);
            btnsil = itemView.findViewById(R.id.Oi_btnSil);
            Durumu=itemView.findViewById(R.id.Oi_ItemState);
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
