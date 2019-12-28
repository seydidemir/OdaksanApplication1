package com.example.odaksanmuhendislikv1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;


public class KullaniciekleSayfasi extends AppCompatActivity {

    EditText isimTxt, soyisimTxt, mailTxt, sifreTxt, departmanTxt, telefonTxt;
    Button kullaniciekleButon;
    Button anasayfaBtn;

    FirebaseAuth firebaseAuth;



    RecyclerView recyclerView;


    FirebaseFirestore db;

    private CollectionReference kullaniciRef = null;

    private KullanicilarAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullaniciekle_sayfasi);

        firebaseAuth = FirebaseAuth.getInstance();

        anasayfaBtn = findViewById(R.id.anasayfaBtn);

        anasayfaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent m = new Intent(KullaniciekleSayfasi.this, AnaSayfa.class);
                startActivity(m);


            }
        });


        db = FirebaseFirestore.getInstance();
        kullaniciRef = db.collection("Kullanicilar");

        isimTxt = findViewById(R.id.muhasebeciisimTxt);
        soyisimTxt = findViewById(R.id.muhasebecisoyisimTxt);
        mailTxt = findViewById(R.id.muhasebecimailTxt);
        sifreTxt = findViewById(R.id.sifreTxt);
        departmanTxt = findViewById(R.id.departmanTxt);
        telefonTxt = findViewById(R.id.muhasebecitelefonTxt);
        kullaniciekleButon = findViewById(R.id.muhasebeciekleButon);
        recyclerView = findViewById(R.id.recyler_view);

        kullaniciekleButon.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {


                addData();

            }

        });


        // setUpRecylerView();

    }


    private void setUpRecylerView() {
        Query query = kullaniciRef.orderBy("departmant", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<Kullanicilar_Liste> options = new FirestoreRecyclerOptions.Builder<Kullanicilar_Liste>()
                .setQuery(query, Kullanicilar_Liste.class)
                .build();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter = new KullanicilarAdapter(options);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                adapter.deleteItem(viewHolder.getAdapterPosition());

            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //adapter.stopListening();
    }

    private void addData() {

        Map<String, Object> user = new HashMap<>();
        user.put("Isim", isimTxt.getText().toString());
        user.put("Soyisim", soyisimTxt.getText().toString());
        user.put("Mail", mailTxt.getText().toString());
        user.put("Şifre", sifreTxt.getText().toString());
        user.put("Departman", departmanTxt.getText().toString());
        user.put("Telefon", telefonTxt.getText().toString());







// Add a new document with a generated ID


        db.collection("Kullanicilar")
                .document(mailTxt.getText().toString())
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


