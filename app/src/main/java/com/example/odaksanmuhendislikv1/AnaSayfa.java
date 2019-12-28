package com.example.odaksanmuhendislikv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AnaSayfa extends AppCompatActivity {

 Button cikisyap;
 Button kullanicilarBtn;
 Button kullaniciEkleBtn;
 Button teklifOlusturBtn;
 Button onaylanmisTekliflerBtn;
 Button onayBekleyenTeklifler;
 Button urunlerBtn;
 Button urunEkleBtn;
 Button muhasebeciEkleBtn;


 FirebaseAuth firebaseAuth;
 private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        kullanicilarBtn=findViewById(R.id.kullanicilarBtn);
        cikisyap=findViewById(R.id.cikisButon);
        kullaniciEkleBtn=findViewById(R.id.kullaniciEkleBtn);
        teklifOlusturBtn=findViewById(R.id.faturalanacakTekliflerBtn);
        onaylanmisTekliflerBtn=findViewById(R.id.faturalanmışTekliflerBtn);
        onayBekleyenTeklifler=findViewById(R.id.onayBekleyenTeklifler);
        urunEkleBtn=findViewById(R.id.urunEkleBtn);
        urunlerBtn=findViewById(R.id.urunEkleBtn);


        urunlerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myInt=new Intent(AnaSayfa.this,Urunler.class);
                startActivity(myInt);
            }
        });


        kullanicilarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myInt=new Intent(AnaSayfa.this,kullanicilar.class);
                startActivity(myInt);
            }
        });

        kullaniciEkleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(AnaSayfa.this,KullaniciEklemeSayfasi.class);
                startActivity(m);
            }
        });

        teklifOlusturBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(AnaSayfa.this,TeklifOlusturmaSayfasi.class);
                startActivity(m);
            }
        });

        onaylanmisTekliflerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(AnaSayfa.this,OnaylanmisTekliflerSayfasi.class);
                startActivity(m);
            }
        });

        onayBekleyenTeklifler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(AnaSayfa.this,TumTeklifleriGorSayfasi.class);
                startActivity(m);
            }
        });

        urunEkleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(AnaSayfa.this,UrunEklemeSayfasi.class);
                startActivity(m);
            }
        });

        cikisyap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                FirebaseAuth .getInstance().signOut();
                Intent i=new Intent(AnaSayfa.this,GirisSayfasi.class);
                startActivity(i);

            }
        });



    }
}
