package com.example.odaksanmuhendislikv1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class KullanicilarAdapter extends FirestoreRecyclerAdapter <Kullanicilar_Liste, KullanicilarAdapter.KullaniciHolder>{

    public KullanicilarAdapter(@NonNull FirestoreRecyclerOptions<Kullanicilar_Liste> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull KullaniciHolder holder, int position, @NonNull Kullanicilar_Liste model) {
        holder.departmanTxt.setText(model.getDepartmanTxt());
        holder.kullaniciAdi.setText(model.getKullaniciAdi());
        holder.kullaniciTel.setText(model.getKullaniciTel());
    }

    @NonNull
    @Override
    public KullaniciHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.kullanicilar_item,
                parent,false);
        return new KullaniciHolder(v);
    }

    public void deleteItem(int position)
    {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class KullaniciHolder extends RecyclerView.ViewHolder{

        TextView kullaniciAdi;
        TextView kullaniciTel;
        TextView departmanTxt;

        public KullaniciHolder(@NonNull View itemView) {
            super(itemView);

            kullaniciAdi=itemView.findViewById(R.id.kullaniciAdi);
            kullaniciTel=itemView.findViewById(R.id.kullaniciTel);
            departmanTxt=itemView.findViewById(R.id.departmanTxt);
        }
    }
}
