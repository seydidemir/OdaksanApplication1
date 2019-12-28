package com.example.odaksanmuhendislikv1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

public class TeklifOlusturmaSayfasi extends AppCompatActivity {


    EditText sirketadiText,teklifbasligiText,yetkiliadiText,urunadiText,verilenteklifText,aciklamaText;
    Button teklifverButon;
    String nakliyat_bicimi="";


    CheckBox odaksanCheck,sirketCheck;

    ProgressDialog pd;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teklif_olusturma_sayfasi);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Teklif Oluşturma");




        sirketadiText=findViewById(R.id.sirketadiText);
        teklifbasligiText=findViewById(R.id.teklifbasligiText);
        yetkiliadiText=findViewById(R.id.yetkiliadiText);
        urunadiText=findViewById(R.id.urunadiText);
        verilenteklifText=findViewById(R.id.verilenteklifText);
        aciklamaText=findViewById(R.id.aciklamaText);


        odaksanCheck= findViewById(R.id.odaksanCheck);
        sirketCheck = findViewById(R.id.sirketCheck);


        teklifverButon=findViewById(R.id.teklifverButon);

        pd=new ProgressDialog(this);
        db=FirebaseFirestore.getInstance();

        teklifverButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sirketadi=sirketadiText.getText().toString().trim();
                String teklifbasligi=teklifbasligiText.getText().toString().trim();
                String yetkiliadi=yetkiliadiText.getText().toString().trim();
                String urunadi=urunadiText.getText().toString().trim();
                String verilenteklif=verilenteklifText.getText().toString().trim();
                String aciklama=aciklamaText.getText().toString().trim();

                if(odaksanCheck.isChecked()) {
                    nakliyat_bicimi="odaksan ödemeli";
                }
                if (sirketCheck.isChecked())
                {
                    nakliyat_bicimi="sirket ödemeli";
                }



                uploadData(sirketadi,teklifbasligi,yetkiliadi,urunadi,verilenteklif,aciklama,nakliyat_bicimi);


            }
        });

    }

    private void uploadData(String sirketadi, String teklifbasligi, String yetkiliadi, String urunadi, String verilenteklif, String aciklama,String nakliyat_bicimi) {

        pd.setTitle("Teklif Oluşturuluyor...");
        pd.show();

        String id= UUID.randomUUID().toString();

        Map<String ,Object>doc=new HashMap<>();
        doc.put("id",id);
        doc.put("sirket_adi",sirketadi);
        doc.put("teklif_basligi",teklifbasligi);
        doc.put("urun_adi",urunadi);
        doc.put("verilen_teklif",verilenteklif);
        doc.put("yetkili_adi",yetkiliadi);
        doc.put("aciklama",aciklama);
        doc.put("nakliyat_bicimi",nakliyat_bicimi);


        db.collection("olusturulan_teklifler").document().set(doc).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                pd.dismiss();
                Toast.makeText(TeklifOlusturmaSayfasi.this,"Güncellendi...",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(TeklifOlusturmaSayfasi.this,e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }



}
