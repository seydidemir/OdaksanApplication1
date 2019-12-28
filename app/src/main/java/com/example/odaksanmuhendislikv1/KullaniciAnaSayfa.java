package com.example.odaksanmuhendislikv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class KullaniciAnaSayfa extends AppCompatActivity {

    Button cikisyap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_ana_sayfa);

        cikisyap=findViewById(R.id.cikisButon);

        cikisyap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(KullaniciAnaSayfa.this,GirisSayfasi.class);
                startActivity(i);
            }
        });




    }


}
