package com.rajendra.vacationtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {

    EditText editTo, editFrom, editArrival, editDeparture, editPrice;
    Button btnUpdate;
    private DatabaseReference MRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        MRef = FirebaseDatabase.getInstance().getReference();

        editTo = findViewById(R.id.editTo1);
        editFrom = findViewById(R.id.editFrom1);
        editArrival = findViewById(R.id.editArrival1);
        editDeparture = findViewById(R.id.editDeparture1);
        editPrice = findViewById(R.id.editPrice1);
        btnUpdate = findViewById(R.id.btnUpdate1);

        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        String name = intent.getStringExtra("name");
        String to = intent.getStringExtra("to");
        String departure = intent.getStringExtra("departure");
        String arrival = intent.getStringExtra("arrival");
        String price = intent.getStringExtra("price");

        editTo.setText(to);
        editFrom.setText(from);
        editArrival.setText(arrival);
        editDeparture.setText(departure);
        editPrice.setText(price);

        String eFrom = editFrom.getText().toString();
        String eTo = editTo.getText().toString();
        String eArrival = editArrival.getText().toString();
        String eDeparture = editDeparture.getText().toString();
        //String ePrice = editPrice.getText().toString();
        String newPrice;

        if((eFrom.equals("Nairobi") && eTo.equals("London")) || (eFrom.equals("London") && eTo.equals("Nairobi"))){
            newPrice = "$ 1200";
        }else if ((eFrom.equals("Nairobi") && eTo.equals("Kampala")) || (eFrom.equals("Kampala") && eTo.equals("Nairobi"))){
            newPrice = "$ 700";
        }else if ((eFrom.equals("Nairobi") && eTo.equals("Amsterdam")) || (eFrom.equals("Amsterdam") && eTo.equals("Nairobi"))){
            newPrice = "$ 900";
        }else if ((eFrom.equals("London") && eTo.equals("Kampala")) || (eFrom.equals("Kampala") && eTo.equals("London"))){
            newPrice = "$ 800";
        }else if ((eFrom.equals("Kampala") && eTo.equals("Amsterdam")) || (eFrom.equals("Amsterdam") && eTo.equals("Kampala"))){
            newPrice = "$ 1100";
        }else {
            newPrice = "$ 0";
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("Booked Flights").child(name).child("from").setValue(editFrom.getText().toString());
                FirebaseDatabase.getInstance().getReference("Booked Flights").child(name).child("to").setValue(editTo.getText().toString());
                FirebaseDatabase.getInstance().getReference("Booked Flights").child(name).child("arrival").setValue(editArrival.getText().toString());
                FirebaseDatabase.getInstance().getReference("Booked Flights").child(name).child("departure").setValue(editDeparture.getText().toString());
                FirebaseDatabase.getInstance().getReference("Booked Flights").child(name).child("price").setValue(newPrice);

                /*String uid = FirebaseAuth.getInstance().getUid();
                if (uid != null) {
                    MRef.child("users").child(uid).child("Flights Booked").push().setValue(map);
                }*/
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
//                    return;
            }
        });
    }
}