package com.example.staj2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AnasayfaActivity extends AppCompatActivity
{
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    TextView dbisim,dbsoyisim,dbkullaniciisim,dbhesaptarih,dbyas;
    ImageView anketicon,kullaniciAnketleriicon;
    String myUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anasayfa);
        tanimla();

        profilBilgiCek();


        anketicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnasayfaActivity.this, AnketBilgi.class);
                startActivity(intent);
            }
        });
        kullaniciAnketleriicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnasayfaActivity.this, SkorGoster.class);
                startActivity(intent);
            }
        });

    }
    public void tanimla()
    {
        dbisim = findViewById(R.id.dbisim);
        dbsoyisim = findViewById(R.id.dbsoyisim);
        dbkullaniciisim = findViewById(R.id.dbkullaniciisim);
        dbhesaptarih = findViewById(R.id.dbhesaptarih);
        dbyas = findViewById(R.id.dbyas);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        myUserid = mAuth.getUid();
        anketicon = findViewById(R.id.anketicon);
        kullaniciAnketleriicon = findViewById(R.id.kullaniciAnketleriicon);

    }
    public void profilBilgiCek() {
        reference.child("Users").child(myUserid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String isim = snapshot.child("isim").getValue().toString();
                String soyisim = snapshot.child("soyisim").getValue().toString();
                String yas = snapshot.child("yas").getValue().toString();
                String kullaniciisim = snapshot.child("kullaniciIsım").getValue().toString();
                String hesaptarih = snapshot.child("HesapOlusturmaTarihi").getValue().toString();


                dbisim.setText("İsim:"+isim);
                dbsoyisim.setText("Soyisim:"+soyisim);
                dbyas.setText("Yaş:"+yas);
                dbkullaniciisim.setText("Kullanıcı Adınız:"+kullaniciisim);
                dbhesaptarih.setText("Hesap oluşturma tarihi:"+hesaptarih);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}