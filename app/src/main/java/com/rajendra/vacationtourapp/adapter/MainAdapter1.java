package com.rajendra.vacationtourapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.rajendra.vacationtourapp.EditActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.Flights;

public class MainAdapter1 extends FirebaseRecyclerAdapter<Flights,MainAdapter1.myViewHolder> {

    public MainAdapter1(@NonNull FirebaseRecyclerOptions<Flights> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Flights model) {
        String to1 = model.getTo();
        holder.to.setText(to1);
        holder.name.setText(model.getName());
        holder.from.setText(model.getFrom());
        holder.arrival.setText(model.getArrival());
        holder.departure.setText(model.getDeparture());
        holder.price.setText(model.getPrice());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = view.getContext();
                Intent myintent = new Intent(context.getApplicationContext(), EditActivity.class);
                myintent.putExtra("from",model.getFrom());
                myintent.putExtra("name",model.getName());
                myintent.putExtra("to",model.getTo());
                myintent.putExtra("departure",model.getDeparture());
                myintent.putExtra("arrival",model.getArrival());
                myintent.putExtra("price", model.getPrice());
                context.startActivity(myintent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference().child("Booked Flights").child(getRef(position).getKey()).removeValue();
            }
        });

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_card,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        TextView to, from, arrival, departure, price, name;
        Button btnEdit, btnDelete;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
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
