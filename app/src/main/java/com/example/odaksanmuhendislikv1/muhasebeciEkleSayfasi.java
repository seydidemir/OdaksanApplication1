package com.example.odaksanmuhendislikv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class muhasebeciEkleSayfasi extends AppCompatActivity {

    EditText muhasebeciisimTxt,muhasebecisoyisimTxt,muhasebecimailTxt,muhasebecisifreTxt,muhasebecitelefonTxt;
    Button muhasebeciekleButon;
    Button anasayfaBtn;

    FirebaseFirestore db;

    private CollectionReference muhasebeciRef=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        muhasebeciekleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

        anasayfaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(muhasebeciEkleSayfasi.this,AnaSayfa.class);
                startActivity(m);
            }
        });


        setContentView(R.layout.activity_muhasebeci_ekle_sayfasi);

        anasayfaBtn=findViewById(R.id.anasayfaBtn);


        db = FirebaseFirestore.getInstance();

        muhasebeciRef = db.collection("Muhasebeciler");

        muhasebeciisimTxt=findViewById(R.id.muhasebeciisimTxt);
        muhasebecisoyisimTxt=findViewById(R.id.muhasebecisoyisimTxt);
        muhasebecimailTxt=findViewById(R.id.muhasebecimailTxt);
        muhasebecisifreTxt=findViewById(R.id.sifreTxt);
        muhasebecitelefonTxt=findViewById(R.id.muhasebecitelefonTxt);
        muhasebeciekleButon=findViewById(R.id.muhasebeciekleButon);

    }
    private void addData() {
        Map<String, Object> user = new HashMap<>();
        user.put("Isim", muhasebeciisimTxt.getText().toString());
        user.put("Soyisim", muhasebecisoyisimTxt.getText().toString());
        user.put("Mail",muhasebecimailTxt.getText().toString());
        user.put("Sifre",muhasebecisifreTxt.getText().toString());
        user.put("Telefon",muhasebecitelefonTxt.getText().toString());


// Add a new document with a generated ID
        db.collection("Muhasebeciler")
                .document(muhasebecimailTxt.getText().toString())
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.w("", " SUCCESS adding document");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("", "Error adding document", e);
                    }
                });
    }
}
