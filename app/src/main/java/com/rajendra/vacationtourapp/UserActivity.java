package com.rajendra.vacationtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rajendra.vacationtourapp.model.User;

public class UserActivity extends AppCompatActivity {

    private ImageView btnHome, btnUser, btnFlights, btnLogOut;
    private Button btnSubmitUser;
    private DatabaseReference tBase;
    private TextView username, email, PNum, password, cardNum, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        btnHome = findViewById(R.id.btnHome);
        btnUser = findViewById(R.id.btnUser);
        btnFlights = findViewById(R.id.btnFlights);
        btnLogOut = findViewById(R.id.btnAccomodations);

        username = findViewById(R.id.Name);
        email = findViewById(R.id.EmailRegister);
        PNum = findViewById(R.id.PhoneNumber);
        password = findViewById(R.id.PasswordRegister);
        cardNum = findViewById(R.id.CreditNumber);
        address = findViewById(R.id.Address);
        

        tBase = FirebaseDatabase.getInstance().getReference().child("users").child(uid);

        btnSubmitUser = findViewById(R.id.btn_submit_user);

        btnSubmitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //writeNewUser();
                String username1 = String.valueOf(username.getText());
                String email1 = String.valueOf(email.getText());
                String PNum1 = String.valueOf(PNum.getText());
                String password1 = String.valueOf(password.getText());
                String cardNum1 = String.valueOf(cardNum.getText());
                String address1 = String.valueOf(address.getText());

                User user = new User(username1, email1, PNum1, password1, cardNum1, address1);

                tBase.setValue(user);
                Toast.makeText(UserActivity.this,"User Details Saved!", Toast.LENGTH_LONG).show();
            }
        });


        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), UserActivity.class);
                startActivity(i);
            }
        });

        btnFlights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), FlightHistory.class);
                startActivity(i);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }

    private void writeNewUser() {
        String username1 = username.getText().toString();
        String email1 = email.getText().toString();
        String PNum1 = PNum.getText().toString();
        String password1 = password.getText().toString();
        String cardNum1 = cardNum.getText().toString();
        String address1 = address.getText().toString();
        
        User user = new User(username1, email1, PNum1, password1, cardNum1, address1);

        tBase.child("users").child(email1).setValue(user);
    }

}