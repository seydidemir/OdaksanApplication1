package com.example.odaksanmuhendislikv1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TümTekliflerListAdapter  extends RecyclerView.Adapter<TümTekliflerListAdapter.VievHolder> {


    private List<Tüm_Teklifler> tüm_tekliflerList;
    public Context context;
    public TümTekliflerListAdapter(Context context, List<Tüm_Teklifler> tüm_tekliflerList){
        this.tüm_tekliflerList = tüm_tekliflerList;
        this.context = context;
    }

    @NonNull
    @Override
    public VievHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent ,false);

       return new VievHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VievHolder holder, int position) {



        holder.statusText.setText(tüm_tekliflerList.get(position).getStatus());
        holder.teklifText.setText(tüm_tekliflerList.get(position).getTeklifler());

        final String teklif_id = tüm_tekliflerList.get(position).teklifID;

        holder.View.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                Toast.makeText(context , "Teklif_id : "+ teklif_id,Toast.LENGTH_SHORT ).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return tüm_tekliflerList.size();
    }

    public  class  VievHolder extends RecyclerView.ViewHolder{

        View View;

        public TextView teklifText;
        public  TextView statusText;


        public VievHolder(@NonNull View itemView) {
            super(itemView);
            View = itemView;

            teklifText = (TextView) View.findViewById(R.id.teklifTxt);
            statusText = (TextView) View.findViewById(R.id.status_text);

        }
    }


}
