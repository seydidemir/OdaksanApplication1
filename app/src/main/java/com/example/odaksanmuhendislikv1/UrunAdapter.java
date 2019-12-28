package com.example.odaksanmuhendislikv1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class UrunAdapter extends FirestoreRecyclerAdapter<Urunler, UrunAdapter.UrunHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public UrunAdapter(@NonNull FirestoreRecyclerOptions<Urunler> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull UrunHolder holder, int position, @NonNull Urunler model) {
        holder.textViewUrunFiyati.setText(model.getTitle());
        holder.textViewUrunAdi.setText(model.getTitle());

    }

    @NonNull
    @Override
    public UrunHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.urun_item,
                parent,false);
        return new UrunHolder(v);
    }

    class  UrunHolder extends RecyclerView.ViewHolder
    {
        TextView textViewUrunAdi;
        TextView textViewUrunFiyati;

        public UrunHolder(@NonNull View itemView) {
            super(itemView);

            textViewUrunAdi=itemView.findViewById(R.id.urunAdi);
            textViewUrunFiyati=itemView.findViewById(R.id.urunFiyat);

        }
    }
}
