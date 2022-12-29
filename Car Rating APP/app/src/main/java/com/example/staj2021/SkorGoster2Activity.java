package com.example.staj2021;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SkorGoster2Activity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String gelenYorumID,hızCevapCek,vitesCevapCek,donanımCevapCek,malzemeCevapCek,mekanCevapCek,sesCevapCek,yolCevapCek,yakıtCevapCek,aracYorumCek;
    TextView hızCevap,vitesCevap,donanımCevap,malzemeCevap,mekanCevap,sesCevap,yolCevap,yakıtCevap,aracYorumCevap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor_goster2);
        gelenYorumID = getIntent().getExtras().getString("gelenYorumID");
        tanimla();
        ratingBilgiCek();


    }
    public void tanimla()
    {

        mAuth= FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        hızCevap= findViewById(R.id.hızCevap);
        vitesCevap= findViewById(R.id.vitesCevap);
        donanımCevap= findViewById(R.id.donanımCevap);
        malzemeCevap= findViewById(R.id.malzemeCevap);
        mekanCevap= findViewById(R.id.mekanCevap);
        sesCevap= findViewById(R.id.sesCevap);
        yolCevap= findViewById(R.id.yolCevap);
        yakıtCevap= findViewById(R.id.yakıtCevap);
        aracYorumCevap = findViewById(R.id.aracYorumCevap);


    }
    public void ratingBilgiCek() {
        databaseReference.child("DegerlendirilenArabalar").child(gelenYorumID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hızCevapCek = snapshot.child("hız").getValue().toString();
                vitesCevapCek = snapshot.child("vites").getValue().toString();
                donanımCevapCek = snapshot.child("donanım").getValue().toString();
                malzemeCevapCek = snapshot.child("malzeme").getValue().toString();
                mekanCevapCek = snapshot.child("mekan").getValue().toString();
                sesCevapCek = snapshot.child("ses").getValue().toString();
                yolCevapCek = snapshot.child("yol").getValue().toString();
                yakıtCevapCek = snapshot.child("yakıt").getValue().toString();
                aracYorumCek = snapshot.child("aracYorum").getValue().toString();

                hızCevap.setText("Hızlanma  "+ hızCevapCek);
                vitesCevap.setText("Vites geçişleri  "+ vitesCevapCek);
                donanımCevap.setText("Donanım Teknolojisi  "+ donanımCevapCek);
                malzemeCevap.setText("Malzeme Kalitesi "+ malzemeCevapCek);
                mekanCevap.setText("İç mekan Kalitesi  "+ mekanCevapCek);
                sesCevap.setText("Ses yalıtımı  "+ sesCevapCek);
                yolCevap.setText("Yol tutuşu "+ yolCevapCek);
                yakıtCevap.setText("Yakıt tüketimi  "+ yakıtCevapCek);
                aracYorumCevap.setText(aracYorumCek);



            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}