package com.example.odaksanmuhendislikv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class OnayBekleyenTekliflerSayfasi extends AppCompatActivity {

    Button anasayfaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        anasayfaBtn=findViewById(R.id.anasayfaBtn);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onay_bekleyen_teklifler_sayfasi);

        anasayfaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(OnayBekleyenTekliflerSayfasi.this,AnaSayfa.class);
                startActivity(m);
            }
        });

    }
}
