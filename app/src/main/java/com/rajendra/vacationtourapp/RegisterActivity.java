package com.rajendra.vacationtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText email_input,password_input;
    Button button;
    FirebaseUser firebaseUser;
    FirebaseAuth my_auth;
    DatabaseReference ref;
    TextView login_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email_input=findViewById(R.id.email_input);
        password_input=findViewById(R.id.password_input);
        button=findViewById(R.id.button);
        login_txt=findViewById(R.id.login_txt);

        my_auth = FirebaseAuth.getInstance();
        firebaseUser = my_auth.getCurrentUser();
        ref = FirebaseDatabase.getInstance().getReference("Registered Users");

        ref.child(firebaseUser.getUid());

        //Button to register user
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();

            }
        });

        //Already exist
        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void createUser(){
        String email = email_input.getText().toString();
        String password = password_input.getText().toString();

        if(TextUtils.isEmpty(email)){
            email_input.setError("Email cannot be empty!");
            email_input.requestFocus();
        }
        else if(TextUtils.isEmpty(password)){
            password_input.setError("Password cannot be empty!");
            password_input.requestFocus();
        }
        else {
            my_auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(RegisterActivity.this,  "Registration Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                    } else {
                        Toast.makeText(RegisterActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    //Check if user is already logged in
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = my_auth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
}