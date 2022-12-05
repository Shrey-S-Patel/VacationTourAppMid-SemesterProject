package com.rajendra.vacationtourapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {

    TextView textView18, textView17, textView16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_details);

        textView18 = findViewById(R.id.textView18);
        textView17 = findViewById(R.id.textView17);
        textView16 = findViewById(R.id.textView16);

        // Get Intent
        Intent intent = getIntent();
        try {
            JSONObject jsonobject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonobject.getJSONObject("response"), intent.getStringExtra("Payment Amount"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void showDetails(JSONObject response, String paymentAmount){
        try{
            textView16.setText(response.getString("id"));
            textView17.setText(response.getString("state"));
            textView18.setText(response.getString(String.format("$%s", paymentAmount)));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}