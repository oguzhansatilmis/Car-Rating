package com.example.staj2021;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.staj2021.AnasayfaActivity;
import com.example.staj2021.KayitolActivity;
import com.example.staj2021.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class GirisActivity extends AppCompatActivity {
    EditText emailText, sifreText;
    Button girisyapButon, kayitOlButon;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String girisemail,girissifre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        tanimla();

        kayitOlButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GirisActivity.this, KayitolActivity.class);
                startActivity(intent);
            }
        });

        girisyapButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(!emailText.getText().toString().isEmpty() && !sifreText.getText().toString().isEmpty())
                {
                    kontrolEt();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Giriş Bilgileriniz Eksik",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void tanimla() {

        girisyapButon = findViewById(R.id.girisyapButon);
        kayitOlButon = findViewById(R.id.kayitOlButon);
        emailText = findViewById(R.id.emailText);
        sifreText = findViewById(R.id.sifreText);
        progressDialog = new ProgressDialog(this);

    }

    public void kontrolEt() {
        girisemail = emailText.getText().toString();
        girissifre = sifreText.getText().toString();
        mAuth = FirebaseAuth.getInstance();

        progressDialog.setTitle("Bilgileriniz Kontrol Ediliyor");
        progressDialog.setMessage("Lütfen Bekleyiniz");
        progressDialog.setCancelable(false);
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(girisemail, girissifre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    Intent intent = new Intent(GirisActivity.this, AnasayfaActivity.class);
                    startActivity(intent);
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(),"Giriş Bilgileriniz Hatalı",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}