package com.rajendra.vacationtourapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.rajendra.vacationtourapp.Config.Config;

import org.json.JSONException;

import java.math.BigDecimal;

public class PaymentSendMoney extends AppCompatActivity {

    private static final int PAYPAL_REQUEST_CODE=7171;

    private static PayPalConfiguration config =new PayPalConfiguration()
        .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)// use Sandbox becuz we on test
        .clientId(Config.PAYPAL_CLIENT_ID);

    Button button2;
    EditText amount;

    String money;

    @Override
    protected void onDestroy(){
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_send_money);

        Intent i = getIntent();
        String price = i.getStringExtra("price");

        // Start Paypal Service
        Intent intent=new Intent(this,PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        startService(intent);

        button2 = findViewById(R.id.button2);
        amount = findViewById(R.id.amount);
        amount.setText(price);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processPayment();
            }
        });
    }

    private void processPayment(){
        money = amount.getText().toString();
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