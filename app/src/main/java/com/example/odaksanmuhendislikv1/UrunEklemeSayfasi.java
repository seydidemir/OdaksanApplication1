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

public class UrunEklemeSayfasi extends AppCompatActivity {


    EditText urunadiText,urunfiyatText;
    Button urunekleButon,anasayfaBtn;

    ProgressDialog pd;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekleme_sayfasi);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Urun Ekleme");


        urunadiText=findViewById(R.id.urunadiText);
        urunfiyatText=findViewById(R.id.urunfiyatText);
        urunekleButon=findViewById(R.id.urunekleButon);
        anasayfaBtn=findViewById(R.id.anasayfaBtn);

        pd=new ProgressDialog(this);

        db=FirebaseFirestore.getInstance();

        urunekleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String urunadi=urunadiText.getText().toString().trim();
                String urunfiyat=urunfiyatText.getText().toString().trim();

                uploadData(urunadi,urunfiyat);
            }

            private void uploadData(String urunadi, String urunfiyat) {

                pd.setTitle("Ürün ekleniyor...");
                pd.show();

                String id= UUID.randomUUID().toString();

                Map<String,Object> doc= new HashMap<>();

                doc.put("Urunadi",urunadi);
                doc.put("Urunfiyati",urunfiyat);
                doc.put("id",id);

                db.collection("Urunler").document(id).set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(UrunEklemeSayfasi.this,"Güncellendi...",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(UrunEklemeSayfasi.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });
    }

}
