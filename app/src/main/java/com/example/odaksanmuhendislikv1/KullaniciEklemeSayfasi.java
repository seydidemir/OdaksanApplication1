package com.example.odaksanmuhendislikv1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KullaniciEklemeSayfasi extends AppCompatActivity {

    EditText kullaniciisimTxt,kullanicitelefonTxt,kullanicisoyisimTxt,kullanicimailTxt,kullanicisifreTxt,kullanicidepartmanTxt;
    Button kullaniciekleButon,anasayfaBtn;

    ProgressDialog pd;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_ekleme_sayfasi);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Kullanıcı Ekleme");


        kullaniciisimTxt=findViewById(R.id.kullaniciisimTxt);
        kullanicisoyisimTxt=findViewById(R.id.kullanicisoyisimTxt);
        kullanicitelefonTxt=findViewById(R.id.kullanicitelefonTxt);
        kullanicimailTxt=findViewById(R.id.kullanicimailTxt);
        kullanicisifreTxt=findViewById(R.id.kullanicisifreTxt);
        kullanicidepartmanTxt=findViewById(R.id.kullanicidepartmanTxt);

        kullaniciekleButon=findViewById(R.id.kullaniciekleButon);
        anasayfaBtn=findViewById(R.id.anasayfaBtn);

        pd=new ProgressDialog(this);
        db=FirebaseFirestore.getInstance();


        kullaniciekleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kullaniciisim=kullaniciisimTxt.getText().toString().trim();
                String kullanicitelefon=kullanicitelefonTxt.getText().toString().trim();
                String kullanicisoyisim=kullanicisoyisimTxt.getText().toString().trim();
                String kullanicimail=kullanicimailTxt.getText().toString().trim();
                String kullanicisifre=kullanicisifreTxt.getText().toString().trim();
                String kullanicidepartman=kullanicidepartmanTxt.getText().toString().trim();
                uploadData(kullaniciisim,kullanicitelefon,kullaniciisim,kullanicisoyisim,kullanicimail,kullanicisifre,kullanicidepartman);
            }
        });
    }

    private void uploadData(String kullaniciisim, String kullanicitelefon, String kullaniciisim1, String kullanicisoyisim, String kullanicimail, String kullanicisifre, String kullanicidepartman) {

        pd.setTitle("Kullanıcı ekleniyor...");
        pd.show();

        String id= UUID.randomUUID().toString();
        Map<String,Object> doc=new HashMap<>();
        doc.put("Isim",kullaniciisim);
        doc.put("Soyisim",kullanicisoyisim);
        doc.put("Telefon",kullanicitelefon);
        doc.put("Departman",kullanicidepartman);
        doc.put("Mail",kullanicimail);
        doc.put("Şifre",kullanicisifre);

        db.collection("Kullanicilar").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                pd.dismiss();
                Toast.makeText(KullaniciEklemeSayfasi.this,"Güncellendi",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                pd.dismiss();
                Toast.makeText(KullaniciEklemeSayfasi.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });


    }
}
