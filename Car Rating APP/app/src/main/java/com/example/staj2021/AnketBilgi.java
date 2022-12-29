package com.example.staj2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AnketBilgi extends AppCompatActivity {

    Button anketDevamEt;
    double mekanSayi,donanımSayi;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String AlmodelBilgisi,AlaracYorum;
    EditText arabamodel,aracYorumEDT;
    RadioButton mekan1,mekan2,mekan3,mekan4,mekan5;
    RadioButton donanım1,donanım2,donanım3,donanım4,donanım5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anketbilgi);

        tanimla();
        mekanPuan();
        donanımPuan();
        anketDevamEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnketBilgi.this, Sorular1Activity.class);
                AlmodelBilgisi = arabamodel.getText().toString();
                AlaracYorum = aracYorumEDT.getText().toString();

                intent.putExtra("mekanSayi",mekanSayi);
                intent.putExtra("donanımSayi",donanımSayi);
                intent.putExtra("modelBilgisi",AlmodelBilgisi);
                intent.putExtra("aracYorum",AlaracYorum);

                startActivity(intent);
            }
        });
    }



    public void tanimla()
    {
        mAuth= FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        arabamodel = findViewById(R.id.arabamodel);
        anketDevamEt = findViewById(R.id.anketDevamEt);
        aracYorumEDT =findViewById(R.id.aracYorum);



        mekan1 = findViewById(R.id.mekan1);
        mekan2 = findViewById(R.id.mekan2);
        mekan3 = findViewById(R.id.mekan3);
        mekan4 = findViewById(R.id.mekan4);
        mekan5 = findViewById(R.id.mekan5);

        donanım1 = findViewById(R.id.donanım1);
        donanım2 = findViewById(R.id.donanım2);
        donanım3 = findViewById(R.id.donanım3);
        donanım4 = findViewById(R.id.donanım4);
        donanım5 = findViewById(R.id.donanım5);
    }
    public void mekanPuan()
    {
        mekan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mekanSayi = 1.0;

            }
        });
        mekan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mekanSayi = 2.0;

            }
        });
        mekan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mekanSayi = 3.0;

            }
        });
        mekan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mekanSayi = 4.0;

            }
        });
        mekan5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mekanSayi = 5.0;

            }
        });
    }

    public void donanımPuan()
    {
        donanım1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donanımSayi = 1.0;

            }
        });
        donanım2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donanımSayi = 2.0;

            }
        });
        donanım3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donanımSayi = 3.0;

            }
        });
        donanım4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donanımSayi = 4.0;

            }
        });
        donanım5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donanımSayi = 5.0;

            }
        });
    }


}