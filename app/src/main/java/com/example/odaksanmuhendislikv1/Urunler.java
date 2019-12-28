package com.example.odaksanmuhendislikv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Urunler extends AppCompatActivity {

    Button anasayfaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urunler);

        anasayfaBtn=findViewById(R.id.anasayfaBtn);

        anasayfaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(Urunler.this,AnaSayfa.class);
                startActivity(m);
            }
        });
    }
}
