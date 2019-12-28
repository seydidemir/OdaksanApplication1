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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class teklifolusturSayfasi extends AppCompatActivity {

    EditText sirketadiText, yetkiliadiText, urunadiText, verilenteklifText, aciklamaText,teklifbasligiText;


    Button teklifverButon;
    Button anasayfaBtn;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teklifolustur_sayfasi);

        db = FirebaseFirestore.getInstance();
        sirketadiText = findViewById(R.id.sirketadiText);
        yetkiliadiText = findViewById(R.id.yetkiliadiText);
        urunadiText = findViewById(R.id.urunadiText);
        verilenteklifText = findViewById(R.id.verilenteklifText);
        aciklamaText = findViewById(R.id.aciklamaText);
        teklifverButon = findViewById(R.id.teklifverButon);
        teklifbasligiText=findViewById(R.id.teklifbasligiText);

        anasayfaBtn=findViewById(R.id.anasayfaBtn);

        anasayfaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(teklifolusturSayfasi.this,AnaSayfa.class);
                startActivity(m);
            }
        });

        teklifverButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        }); }

        private void addData () {
            Map<String, Object> user = new HashMap<>();
            user.put("sirket_adi", sirketadiText.getText().toString());
            user.put("yetkili_adi", yetkiliadiText.getText().toString());
            user.put("urun_adi", urunadiText.getText().toString());
            user.put("verilen_teklif", sirketadiText.getText().toString());
            user.put("aciklama", aciklamaText.getText().toString());
            user.put("teklif_basligi",teklifbasligiText.getText().toString());



// Add a new document with a generated ID
            db.collection("olusturulan_teklifler")
                    .document(teklifbasligiText.getText().toString())
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

