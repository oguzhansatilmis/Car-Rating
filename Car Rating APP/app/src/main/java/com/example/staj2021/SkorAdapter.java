package com.example.staj2021;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SkorAdapter extends RecyclerView.Adapter<SkorAdapter.ViewHolder>{

    Context context;
    List<String> aracModel,ratingSkor,yorumIDListe;
    Activity activity;

    public SkorAdapter(Context context, List<String> aracModel,List<String> ratingSkor,List<String> yorumIDListe,Activity activity)
    {
        this.context = context;
        this.aracModel = aracModel;
        this.ratingSkor = ratingSkor;
        this.yorumIDListe = yorumIDListe;
        this.activity = activity;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.skor_layout,parent,false);
        return  new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        holder.rating.setText("Skor: "+ ratingSkor.get(position).toString());
        holder.model.setText("Aracın modeli: "+aracModel.get(position).toString());

        holder.kullaniciGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(activity,SkorGoster2Activity.class);
               intent.putExtra("gelenYorumID",yorumIDListe.get(position));
                activity.startActivity(intent);
            }
        });




    }
    @Override
    public int getItemCount()
    {
        return aracModel.size();
    }
    public class  ViewHolder extends RecyclerView.ViewHolder
    {
        TextView model,rating;
        RelativeLayout kullaniciGoster;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            model = itemView.findViewById(R.id.model);
            rating = itemView.findViewById(R.id.rating);
            kullaniciGoster = itemView.findViewById(R.id.kullaniciGoster);

        }
    }



}
