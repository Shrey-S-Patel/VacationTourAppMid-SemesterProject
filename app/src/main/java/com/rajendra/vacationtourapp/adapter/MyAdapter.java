package com.rajendra.vacationtourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.Flights;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Flights> list;

    public MyAdapter(Context context, ArrayList<Flights> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.flight_card, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Flights flight = list.get(position);
        holder.to.setText(flight.getTo());
        holder.from.setText(flight.getFrom());
        holder.arrival.setText(flight.getArrival());
        holder.departure.setText(flight.getDeparture());
        holder.price.setText(flight.getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView to, from, arrival, departure, price;
        Button btnEdit, btnDelete;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            to = itemView.findViewById(R.id.EndLocation);
            from = itemView.findViewById(R.id.StartLocation);
            arrival = itemView.findViewById(R.id.Arrival);
            departure = itemView.findViewById(R.id.Departure);
            price = itemView.findViewById(R.id.Price);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}
