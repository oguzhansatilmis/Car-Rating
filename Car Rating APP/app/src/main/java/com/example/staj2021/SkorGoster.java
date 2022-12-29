package com.example.staj2021;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SkorGoster extends AppCompatActivity {
    List<String> aracModel,degerlendirenKullanici,ratingSkor,yorumkey;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth auth;
    String myUserid;
    RecyclerView userRecyView;
    SkorAdapter skorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skorgoster);
        tanimla();
        listele();

    }
    public void tanimla()
    {
        aracModel = new ArrayList<>();
        degerlendirenKullanici = new ArrayList<>();
        ratingSkor = new ArrayList<>();
        yorumkey = new ArrayList<>();
        firebaseDatabase = firebaseDatabase.getInstance();
        reference =firebaseDatabase.getReference();
        auth = FirebaseAuth.getInstance();
        userRecyView = findViewById(R.id.recyc_kullanicilar);
        myUserid = auth.getUid();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SkorGoster.this);
        userRecyView.setLayoutManager(layoutManager);
        skorAdapter = new SkorAdapter(SkorGoster.this,aracModel,ratingSkor,yorumkey,SkorGoster.this);
        userRecyView.setAdapter(skorAdapter);
    }
    public void listele()
    {
        reference.child("DegerlendirilenArabalar").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String yorumkeylerial = snapshot.getKey();
                String aracModelleri = snapshot.child("modelBilgisi").getValue().toString();
                String degerlendirelenRatingSkor = snapshot.child("rating").getValue().toString();
                yorumkey.add(yorumkeylerial);
                aracModel.add(aracModelleri);
                ratingSkor.add(degerlendirelenRatingSkor);
                skorAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}