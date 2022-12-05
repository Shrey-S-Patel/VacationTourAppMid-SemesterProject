package com.rajendra.vacationtourapp;

import android.app.Activity;
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
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.rajendra.vacationtourapp.Config.Config;

import org.json.JSONException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ResultsActivity extends AppCompatActivity {

    ImageView btnHome, btnUser, btnFlights, btnLogOut;
    private DatabaseReference MRef;
    TextView fromTo, iPrice, date1, date2, total, quantity;
    Button removeBtn, bookBtn;
    ImageView plusQ, minusQ;
    private Integer fQuantity = 1;
    String money;
    Integer count = 0;

    private static final int PAYPAL_REQUEST_CODE=7171;

    private static PayPalConfiguration config =new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)// use Sandbox becuz we on test
            .clientId(Config.PAYPAL_CLIENT_ID);

    @Override
    protected void onDestroy(){
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Start Paypal Service
        Intent myintent=new Intent(this,PayPalService.class);
        myintent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(myintent);

        //recyclerView = findViewById(R.id.rv_results);

        bookBtn = findViewById(R.id.bookBtn);

        btnHome = findViewById(R.id.btnHome);
        btnUser = findViewById(R.id.btnUser);
        btnFlights = findViewById(R.id.btnFlights);
        btnLogOut = findViewById(R.id.btnAccomodations);


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
                Intent i = new Intent(getApplicationContext(), BookingsActivity.class);
                startActivity(i);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), FlightHistory.class));
            }
        });

        //Initializing the variables and getting intent data from the previous activity
        fromTo = findViewById(R.id.Unametext);
        iPrice = findViewById(R.id.Inametext);
        total = findViewById(R.id.total);
        date1 = findViewById(R.id.date1Text);
        date2 = findViewById(R.id.date2Text);
        quantity = findViewById(R.id.quantity_txt);
        plusQ = findViewById(R.id.add_quantity);
        minusQ = findViewById(R.id.reduce_quantity);

        Intent intent = getIntent();
        String from = intent.getStringExtra("from");
        String to = intent.getStringExtra("to");
        String date1txt = intent.getStringExtra("date1");
        String date2txt = intent.getStringExtra("date2");
        String price = intent.getStringExtra("price");


        fromTo.setText(from + " -> " + to);
        iPrice.setText(price);
        date1.setText(date1txt);
        date2.setText(date2txt);

        money = iPrice.getText().toString();

        MRef = FirebaseDatabase.getInstance().getReference();

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fromToCount = from + to + count;
                Map<String, String> map = new HashMap<>();
                map.put("name",fromToCount);
                map.put("from", from);
                map.put("to", to );
                map.put("departure", date1txt );
                map.put("arrival", date2txt);
                map.put("price", price);

                MRef.child("Booked Flights").child(fromToCount).setValue(map);

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                if (uid != null) {
                    MRef.child("users").child(uid).child("Booked Flights").child(fromToCount).setValue(map);
                }
                Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_LONG).show();
//                    return;
                count++;

                Intent i = new Intent(getApplicationContext(), PaymentSendMoney.class);
                i.putExtra("price", price);
                startActivity(i);
            }
        });

    }
    private void processPayment(){
        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(money)),"USD",
                "Payment for Flight", PayPalPayment.PAYMENT_INTENT_SALE);
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PAYPAL_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirmation != null) {
                }
                try {
                    String paymentDetails = confirmation.toJSONObject().toString(4);
                    startActivity(new Intent(this, PaymentDetails.class)
                            .putExtra("PaymentDetails",paymentDetails)
                            .putExtra("Payment Amount",money)
                    );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        }
        else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
    }

}