package com.example.staj2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Sorular1Activity extends AppCompatActivity
{

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String modelBilgisi,key,aracYorum,myUserid,mekanSayiSTR,donanımSayiSTR;

    RadioButton yakit1,yakit2,yakit3,yakit4,yakit5;
    RadioButton malzeme1,malzeme2,malzeme3,malzeme4,malzeme5;
    RadioButton yol1,yol2,yol3,yol4,yol5;
    RadioButton hız1,hız2,hız3,hız4,hız5;
    RadioButton ses1,ses2,ses3,ses4,ses5;
    RadioButton vites1,vites2,vites3,vites4,vites5;
    double yakitSayi;
    double malzemeSayi;
    double yolSayi;
    double hızSayi;
    double sesSayi;
    double vitesSayi;
    double ortalamaSayi;
    double doublemekanSayi;
    double doubledonanımSayi;
    TextView deneme;
    Button anketbitir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorular1);
        modelBilgisi =  getIntent().getExtras().getString("modelBilgisi");
        aracYorum =  getIntent().getExtras().getString("aracYorum");
        doublemekanSayi =  getIntent().getExtras().getDouble("mekanSayi");
        doubledonanımSayi =  getIntent().getExtras().getDouble("donanımSayi");

        tanimla();
        yakitPuan();
        malzemePuan();
        yolPuan();
        hızPuan();
        sesPuan();
        vitesPuan();


        anketbitir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anketKayıt();
                Intent intent = new Intent(Sorular1Activity.this, AracRatingActivity.class);
                intent.putExtra("key",key);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Değerlendirmeniz için teşekkür ederiz.",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void tanimla()
    {
        mAuth= FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        myUserid = mAuth.getUid();


        yakit1 = findViewById(R.id.yakit1);
        yakit2 = findViewById(R.id.yakit2);
        yakit3 = findViewById(R.id.yakit3);
        yakit4 = findViewById(R.id.yakit4);
        yakit5 = findViewById(R.id.yakit5);

        malzeme1 = findViewById(R.id.malzeme1);
        malzeme2 = findViewById(R.id.malzeme2);
        malzeme3 = findViewById(R.id.malzeme3);
        malzeme4 = findViewById(R.id.malzeme4);
        malzeme5 = findViewById(R.id.malzeme5);

        yol1 = findViewById(R.id.yol1);
        yol2 = findViewById(R.id.yol2);
        yol3 = findViewById(R.id.yol3);
        yol4 = findViewById(R.id.yol4);
        yol5 = findViewById(R.id.yol5);

        hız1 = findViewById(R.id.hız1);
        hız2 = findViewById(R.id.hız2);
        hız3 = findViewById(R.id.hız3);
        hız4 = findViewById(R.id.hız4);
        hız5 = findViewById(R.id.hız5);

        ses1 = findViewById(R.id.ses1);
        ses2 = findViewById(R.id.ses2);
        ses3 = findViewById(R.id.ses3);
        ses4 = findViewById(R.id.ses4);
        ses5 = findViewById(R.id.ses5);

        vites1 = findViewById(R.id.vites1);
        vites2 = findViewById(R.id.vites2);
        vites3 = findViewById(R.id.vites3);
        vites4 = findViewById(R.id.vites4);
        vites5 = findViewById(R.id.vites5);

        deneme  = findViewById(R.id.deneme);
        anketbitir = findViewById(R.id.anketbitir);
    }
    public void yakitPuan()
    {
        yakit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yakitSayi = 1.0;

            }
        });
        yakit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yakitSayi = 2.0;

            }
        });
        yakit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yakitSayi = 3.0;

            }
        });
        yakit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yakitSayi = 4.0;

            }
        });
        yakit5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yakitSayi = 5.0;

            }
        });
    }
    public void malzemePuan()
    {
        malzeme1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                malzemeSayi = 1.0;

            }
        });
        malzeme2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                malzemeSayi = 2.0;

            }
        });
        malzeme3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                malzemeSayi = 3.0;

            }
        });
        malzeme4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                malzemeSayi = 4.0;

            }
        });
        malzeme5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                malzemeSayi = 5.0;

            }
        });
    }
    public void yolPuan()
    {
        yol1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yolSayi = 1.0;

            }
        });
        yol2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yolSayi = 2.0;

            }
        });
        yol3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yolSayi = 3.0;

            }
        });
        yol4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yolSayi = 4.0;

            }
        });
        yol5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yolSayi = 5.0;

            }
        });
    }
    public void hızPuan()
    {
        hız1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hızSayi = 1.0;

            }
        });
        hız2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hızSayi = 2.0;

            }
        });
        hız3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hızSayi = 3.0;

            }
        });
        hız4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hızSayi = 4.0;

            }
        });
        hız5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hızSayi = 5.0;

            }
        });
    }
    public void sesPuan()
    {
        ses1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesSayi = 1.0;

            }
        });
        ses2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesSayi = 2.0;

            }
        });
        ses3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesSayi = 3.0;

            }
        });
        ses4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesSayi = 4.0;

            }
        });
        ses5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sesSayi = 5.0;

            }
        });
    }
    public void vitesPuan()
    {
        vites1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitesSayi = 1.0;

            }
        });
        vites2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitesSayi = 2.0;

            }
        });
        vites3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitesSayi = 3.0;

            }
        });
        vites4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitesSayi = 4.0;

            }
        });
        vites5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitesSayi = 5.0;

            }
        });
    }
    public void ortalamaHesapla()
    {
        ortalamaSayi = (yakitSayi+malzemeSayi+sesSayi+hızSayi+vitesSayi+malzemeSayi+doubledonanımSayi+doublemekanSayi)/8;
        Math.round(ortalamaSayi);

    }

    public void anketKayıt()
    {
        key = databaseReference.child("DegerlendirilenArabalar").push().getKey(); //mesajların dbye eklenmesi uniq idler olusturuldu
        ortalamaHesapla();
        Map anketMap = new HashMap<>();
        anketMap.put("modelBilgisi",modelBilgisi);
        anketMap.put("aracYorum",aracYorum);
        anketMap.put("mekan",doublemekanSayi);
        anketMap.put("donanım",doubledonanımSayi);
        anketMap.put("yakıt",yakitSayi);
        anketMap.put("malzeme",malzemeSayi);
        anketMap.put("yol",yolSayi);
        anketMap.put("hız",hızSayi);
        anketMap.put("ses",sesSayi);
        anketMap.put("vites",vitesSayi);
        anketMap.put("rating",ortalamaSayi);

        databaseReference.child("DegerlendirilenArabalar").child(key).setValue(anketMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Değerlendirme Başarılı",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}