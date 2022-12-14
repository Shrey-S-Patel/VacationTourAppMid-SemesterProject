package com.rajendra.vacationtourapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText email_input,password_input;
    Button button;
    FirebaseAuth my_auth;
    TextView login_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_input=findViewById(R.id.email_input);
        password_input=findViewById(R.id.password_input);
        button=findViewById(R.id.button);
        login_txt=findViewById(R.id.login_txt);

        my_auth = FirebaseAuth.getInstance();

        //Button to login
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logUser();
            }
        });

        //Does not exist
        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    //Log in
    private void logUser(){
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
        else{
            my_auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity( new Intent(LoginActivity.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
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
        if(currentUser != null) {
            currentUser.reload();
        }
    }
}