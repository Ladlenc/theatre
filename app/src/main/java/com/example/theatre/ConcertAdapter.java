package com.example.theatre;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConcertAdapter extends RecyclerView.Adapter<ConcertAdapter.ViewHolder> implements Filterable {
    private ArrayList<Concert> concertData;
    private ArrayList<Concert> concertDataAll;
    private Context con;
    private int lastpos = -1;



    public ConcertAdapter(Context cont, ArrayList<Concert> concerts) {
        this.concertData= concerts;
        this.concertDataAll= concerts;
        con=cont;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(con).inflate(R.layout.card_view,parent,false));
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.title.setText(concertData.get(position).getTitle());
        holder.info.setText(concertData.get(position).getInfo());
        holder.actors.setText(concertData.get(position).getActors());

    }

    @Override
    public int getItemCount() {
        return concertData.size();
    }

    private Filter concertFIlter= new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Concert> filterlist = new ArrayList<>();
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0){
                results.count = concertDataAll.size();
                results.values = concertDataAll;
            }else{
                String searchword = constraint.toString().toLowerCase().trim();
                for (Concert con : concertDataAll){
                    if(con.getTitle().toLowerCase().contains(searchword)){
                        filterlist.add(con);
                    }
                }

                results.count=filterlist.size();
                results.values = filterlist;
            }

            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            concertData = (ArrayList) results.values;
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return null;
    }




    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView info;
        TextView actors;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            info = itemView.findViewById(R.id.concert_text);
            actors = itemView.findViewById(R.id.concert_actors);
        }

        public void bindTo(Concert current) {
            title.setText(current.getTitle());
            info.setText(current.getInfo());
            actors.setText(current.getActors());


        }
    };


}

