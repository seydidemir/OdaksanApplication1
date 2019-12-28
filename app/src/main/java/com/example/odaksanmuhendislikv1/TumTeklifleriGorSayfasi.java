package com.example.odaksanmuhendislikv1;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TumTeklifleriGorSayfasi extends AppCompatActivity {


    private static final String TAG = "FireLog";
    private RecyclerView analiste;
    private FirebaseFirestore Firestore;
    private TümTekliflerListAdapter tekliflerListAdapter;

    private List<Tüm_Teklifler> tüm_tekliflerList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tum_teklifleri_gor_sayfasi);

        tüm_tekliflerList =new ArrayList<>();
        tekliflerListAdapter = new TümTekliflerListAdapter(getApplicationContext(), tüm_tekliflerList);
        analiste = (RecyclerView) findViewById(R.id.ana_liste);
        analiste.setHasFixedSize(true);
        analiste.setLayoutManager(new LinearLayoutManager(this));
        analiste.setAdapter(tekliflerListAdapter);


        Firestore = FirebaseFirestore.getInstance();

        Firestore.collection("olusturulan_teklifler").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {


            if(e != null){

                Log.d(TAG, "Error:"+ e.getMessage());
                return ;
            }

            for(DocumentChange doc: documentSnapshots.getDocumentChanges()){

            if(doc.getType() == DocumentChange.Type.ADDED){

                    String teklifId = doc.getDocument().getId();

                Tüm_Teklifler tüm_teklifler = doc.getDocument().toObject(Tüm_Teklifler.class).withId(teklifId);


                tüm_tekliflerList.add(tüm_teklifler);

               tekliflerListAdapter.notifyDataSetChanged();

            }





            }
            }
        });


    }
}
