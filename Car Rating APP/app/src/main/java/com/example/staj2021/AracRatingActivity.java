package com.example.staj2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AracRatingActivity extends AppCompatActivity
{
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView arabaModelYanit,arabaRatingText,hızCevap,vitesCevap,donanımCevap,malzemeCevap,mekanCevap,sesCevap,yolCevap,yakıtCevap;
    String gelenkey,rating,gelenAracModel,hızCevapCek,vitesCevapCek,donanımCevapCek,malzemeCevapCek,mekanCevapCek,sesCevapCek,yolCevapCek,yakıtCevapCek;
    ImageView ratingImage;
    Button geriDon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arac_rating);
        gelenkey = getIntent().getExtras().getString("key");
        tanimla();
        ratingBilgiCek();
        geriDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(AracRatingActivity.this,AnasayfaActivity.class);
                startActivity(intent);
            }
        });
    }
    public void tanimla()
    {

        mAuth= FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        ratingImage  = findViewById(R.id.ratingImage);
        arabaModelYanit = findViewById(R.id.arac1);
        arabaRatingText = findViewById(R.id.arac2);

        geriDon = findViewById(R.id.geriDon);
        hızCevap = findViewById(R.id.hızCevap);
        vitesCevap = findViewById(R.id.vitesCevap);
        donanımCevap = findViewById(R.id.donanımCevap);
        malzemeCevap = findViewById(R.id.malzemeCevap);
        mekanCevap = findViewById(R.id.mekanCevap);
        sesCevap = findViewById(R.id.sesCevap);
        yolCevap = findViewById(R.id.yolCevap);
        yakıtCevap = findViewById(R.id.yakıtCevap);

    }
    public void ratingBilgiCek() {
        databaseReference.child("DegerlendirilenArabalar").child(gelenkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                rating = snapshot.child("rating").getValue().toString();

                hızCevapCek = snapshot.child("hız").getValue().toString();
                vitesCevapCek = snapshot.child("vites").getValue().toString();
                donanımCevapCek = snapshot.child("donanım").getValue().toString();
                malzemeCevapCek = snapshot.child("malzeme").getValue().toString();
                mekanCevapCek = snapshot.child("mekan").getValue().toString();
                sesCevapCek = snapshot.child("ses").getValue().toString();
                yolCevapCek = snapshot.child("yol").getValue().toString();
                yakıtCevapCek = snapshot.child("yakıt").getValue().toString();
                gelenAracModel = snapshot.child("modelBilgisi").getValue().toString();

                arabaModelYanit.setText("Aracın Modeli "+ gelenAracModel);
                arabaRatingText.setText("Verdiğiniz Skor  "+ rating);
                hızCevap.setText("Hızlanma  "+ hızCevapCek);
                vitesCevap.setText("Vites geçişleri  "+ vitesCevapCek);
                donanımCevap.setText("Donanım Teknolojisi  "+ donanımCevapCek);
                malzemeCevap.setText("Malzeme Kalitesi "+ malzemeCevapCek);
                mekanCevap.setText("İç mekan Kalitesi  "+ mekanCevapCek);
                sesCevap.setText("Ses yalıtımı  "+ sesCevapCek);
                yolCevap.setText("Yol tutuşu "+ yolCevapCek);
                yakıtCevap.setText("Yakıt tüketimi  "+ yakıtCevapCek);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}