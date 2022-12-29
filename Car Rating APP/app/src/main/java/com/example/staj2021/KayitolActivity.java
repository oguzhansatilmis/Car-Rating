package com.example.staj2021;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class KayitolActivity extends AppCompatActivity {

     FirebaseAuth mAuth;
     DatabaseReference mDatabase;
    EditText emailTextKayit, sifreTextKayit,kullaniciAdi,kullaniciIsım,kullaniciSoyisim,kullaniciYas;
    Button kayitOl;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitol);
        tanimla();
    }


    public void tanimla() {
        mAuth= FirebaseAuth.getInstance();
        emailTextKayit = findViewById(R.id.emailTextKayit);
        sifreTextKayit = findViewById(R.id.sifreTextKayit);
        kullaniciAdi = findViewById(R.id.kullaniciAdi);
        kullaniciIsım = findViewById(R.id.kullaniciIsım);
        kullaniciSoyisim = findViewById(R.id.kullaniciSoyisim);
        kullaniciYas = findViewById(R.id.kullaniciYas);

        kayitOl = findViewById(R.id.kayitOl);
        progressDialog = new ProgressDialog(this);
        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kayitOlDonustur();
            }
        });


    }
    public void kayitOlDonustur() {
        String eposta = emailTextKayit.getText().toString();
        String sifre = sifreTextKayit.getText().toString();
        String kullaniciisim = kullaniciAdi.getText().toString();
        String isim = kullaniciIsım.getText().toString();
        String soyisim = kullaniciSoyisim.getText().toString();
        String yas = kullaniciYas.getText().toString();

        progressDialog.setTitle("Lütfen bekleyiniz");
        progressDialog.setMessage("Kayit olma işlemi gerçekleşiyor");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (!TextUtils.isEmpty(eposta) || !TextUtils.isEmpty(sifre))
        {

           registerUser(eposta, sifre,kullaniciisim,isim,soyisim,yas);
        }


    }
    public void registerUser (String eposta,String sifre,String kullaniciisim,String isim,String soyisim,String yas){

        mAuth.createUserWithEmailAndPassword(eposta,sifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    String user_id =mAuth.getCurrentUser().getUid();  // uniq user id
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                    HashMap<String,String> userMap = new HashMap<>();
                    userMap.put("eposta",eposta);
                    userMap.put("sifre",sifre);
                    userMap.put("kullaniciIsım",kullaniciisim);
                    userMap.put("isim",isim);
                    userMap.put("soyisim",soyisim);
                    userMap.put("yas",yas);
                    userMap.put("HesapOlusturmaTarihi",gunTarih());

                    mDatabase.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            progressDialog.dismiss();
                            Intent intent = new Intent(KayitolActivity.this, GirisActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(),"Kayıt olma işlemi başarılı",Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Kayıt olma işlemi gerçekleşmedi",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }
    public String gunTarih()
    {
        DateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
        Date date = new Date();
        String guntarih = dateFormat.format(date);

        return guntarih;
    }

}
