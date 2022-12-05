package com.rajendra.vacationtourapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rajendra.vacationtourapp.adapter.MainAdapter1;
import com.rajendra.vacationtourapp.adapter.MyAdapter;
import com.rajendra.vacationtourapp.model.Flights;

import java.util.ArrayList;

public class FlightHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    MainAdapter1 mainAdapter1;
    ArrayList<Flights> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_history);

        recyclerView = findViewById(R.id.flightList);

        database = FirebaseDatabase.getInstance().getReference("Booked Flights");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Flights> options =
                new FirebaseRecyclerOptions.Builder<Flights>()
                        .setQuery(database, Flights.class)
                        .build();

        mainAdapter1 = new MainAdapter1(options);
        recyclerView.setAdapter(mainAdapter1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter1.stopListening();
    }
}