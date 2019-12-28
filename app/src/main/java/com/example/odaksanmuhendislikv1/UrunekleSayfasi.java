package com.example.odaksanmuhendislikv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class UrunekleSayfasi extends AppCompatActivity {

    EditText urunadiText,urunfiyatText;
    Button urunekleButon;
    FirebaseFirestore db;
    Button anasayfaBtn;

    private CollectionReference urunRef=null;
    private UrunAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urunekle_sayfasi);

        //setUpRecyleView();


        anasayfaBtn=findViewById(R.id.anasayfaBtn);

        anasayfaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(UrunekleSayfasi.this,AnaSayfa.class);
                startActivity(m);
            }
        });

        db = FirebaseFirestore.getInstance();
        urunRef=db.collection("Urunler");

        urunadiText=findViewById(R.id.urunadiText);
        urunfiyatText=findViewById(R.id.urunfiyatText);
        urunekleButon=findViewById(R.id.urunekleButon);


 urunekleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });

    }

    private void setUpRecyleView()
    {
        Query query=urunRef.orderBy("urunAdi",Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Urunler> options=new FirestoreRecyclerOptions.Builder<Urunler>()
                .setQuery(query,Urunler.class)
                .build();

        adapter=new UrunAdapter(options);

        RecyclerView recyclerView=findViewById(R.id.recyler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
      //  adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
       // adapter.stopListening();
    }

    private void addData() {
        Map<String, Object> user = new HashMap<>();
        user.put("Ürünadi", urunadiText.getText().toString());
        user.put("Ürünfiyati", urunfiyatText.getText().toString());



// Add a new document with a generated ID
        db.collection("Urunler")
                .document(urunadiText.getText().toString())
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
