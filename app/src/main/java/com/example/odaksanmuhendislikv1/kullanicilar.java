package com.example.odaksanmuhendislikv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class kullanicilar extends AppCompatActivity {

    Button anasayfaBtn;
    FirebaseFirestore db;

    private CollectionReference kullaniciRef=null;

    private KullanicilarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanicilar);


        anasayfaBtn=findViewById(R.id.anasayfaBtn);
        anasayfaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(kullanicilar.this,AnaSayfa.class);
                startActivity(m);
            }
        });
    }
}
