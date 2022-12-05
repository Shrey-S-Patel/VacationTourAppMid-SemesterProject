package com.rajendra.vacationtourapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.ArrayList;
import java.util.Calendar;

public class BookingsActivity extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar, calendar1;
    private EditText txtdate,txtdate2;
    private int year, month, day;
    private int year1, month1, day1;

    private Button btnSearch;
    private EditText TextPrice;

    private EditText textView1,textView2;
    private ArrayList<String> arrayList;
    private Dialog dialog;

    private GridLayoutManager gridLayoutManager;

    private String from, to, date1, date2, newPrice;
    private Spinner spinnerLocation, spinnerLocation1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);

        btnSearch = findViewById(R.id.btnSearch);
        spinnerLocation=findViewById(R.id.spinner_Locations);
        spinnerLocation1 = findViewById(R.id.spinner_Locations1);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.locations, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerLocation.setAdapter(adapter);
        spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = parent.getItemAtPosition(position).toString();

                if((from.equals("Nairobi") && to.equals("London")) || (from.equals("London") && to.equals("Nairobi"))){
                    newPrice = "$ 1200";
                }else if ((from.equals("Nairobi") && to.equals("Kampala")) || (from.equals("Kampala") && to.equals("Nairobi"))){
                    newPrice = "$ 700";
                }else if ((from.equals("Nairobi") && to.equals("Amsterdam")) || (from.equals("Amsterdam") && to.equals("Nairobi"))){
                    newPrice = "$ 900";
                }else if ((from.equals("London") && to.equals("Kampala")) || (from.equals("Kampala") && to.equals("London"))){
                    newPrice = "$ 800";
                }else if ((from.equals("Kampala") && to.equals("Amsterdam")) || (from.equals("Amsterdam") && to.equals("Kampala"))){
                    newPrice = "$ 1100";
                }else {
                    newPrice = "$ 0";
                }

                Toast.makeText(parent.getContext(), from, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerLocation1.setAdapter(adapter);
        spinnerLocation1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = parent.getItemAtPosition(position).toString();
                if((from.equals("Nairobi") && to.equals("London")) || (from.equals("London") && to.equals("Nairobi"))){
                    newPrice = "$ 1200";
                }else if ((from.equals("Nairobi") && to.equals("Kampala")) || (from.equals("Kampala") && to.equals("Nairobi"))){
                    newPrice = "$ 700";
                }else if ((from.equals("Nairobi") && to.equals("Amsterdam")) || (from.equals("Amsterdam") && to.equals("Nairobi"))){
                    newPrice = "$ 900";
                }else if ((from.equals("London") && to.equals("Kampala")) || (from.equals("Kampala") && to.equals("London"))){
                    newPrice = "$ 800";
                }else if ((from.equals("Kampala") && to.equals("Amsterdam")) || (from.equals("Amsterdam") && to.equals("Kampala"))){
                    newPrice = "$ 1100";
                }else {
                    newPrice = "$ 0";
                }
                Toast.makeText(parent.getContext(), to, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //Layout manager for search result recyclerview
        gridLayoutManager = new GridLayoutManager(this , 1, GridLayoutManager.VERTICAL,false);
        txtdate = findViewById(R.id.TextDate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate1(year, month+1, day);

        txtdate2 = findViewById(R.id.TextDate2);
        calendar1 = Calendar.getInstance();
        year1 = calendar1.get(Calendar.YEAR);
        month1 = calendar1.get(Calendar.MONTH);
        day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        showDate2(year1, month1+1, day1);

        //Search button to go to the next booking activity.
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent myintent = new Intent(getApplicationContext(),ResultsActivity.class);
                myintent.putExtra("from",from);
                myintent.putExtra("to",to);
                myintent.putExtra("date1",date1);
                myintent.putExtra("date2",date2);
                myintent.putExtra("price", newPrice);
                context.startActivity(myintent);
            }
        });


    }

    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                        Toast.LENGTH_SHORT)
                .show();
    }

    public void setDate2(View view) {
        showDialog(1000);
        Toast.makeText(getApplicationContext(), "ca",
                        Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            DatePickerDialog DPDialog = new DatePickerDialog(this,
                    myDateListener, year, month, day);
            DPDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return DPDialog;
        }

        if (id == 1000) {
            DatePickerDialog DPDialog2 = new DatePickerDialog(this,
                    myDateListener2, year, month, day);
            DPDialog2.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            return DPDialog2;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate1(arg1, arg2+1, arg3);
                }
            };

    private DatePickerDialog.OnDateSetListener myDateListener2 = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate2(arg1, arg2+1, arg3);
                }
            };


    private void showDate1(int year, int month, int day) {
        StringBuilder sb = new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year);
        date1 = sb.toString();
        txtdate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void showDate2(int year, int month, int day) {
        StringBuilder sb = new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year);
        date2 = sb.toString();
        txtdate2.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

}