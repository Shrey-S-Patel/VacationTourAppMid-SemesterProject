package com.rajendra.vacationtourapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.AirlinesModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AirListAdapter extends ArrayAdapter<AirlinesModel> {

    // constructor for our list view adapter.
    public AirListAdapter(@NonNull Context context, ArrayList<AirlinesModel> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.image_lv_item, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        AirlinesModel dataModal = getItem(position);

        // initializing our UI components of list view item.
        TextView airlineName = listitemView.findViewById(R.id.Unametext);
        TextView price = listitemView.findViewById(R.id.Inametext);
        Button place = listitemView.findViewById(R.id.endBtn);
        ImageView img1 = listitemView.findViewById(R.id.img1);

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        airlineName.setText(dataModal.getAhmedabad());
        price.setText(dataModal.getAhmedabad());
        place.setText(dataModal.getAhmedabad());

        // in below line we are using Picasso to
        // load image from URL in our Image VIew.
        Picasso.get().load(dataModal.getImgUrl()).into(img1);

        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Toast.makeText(getContext(), "Item clicked is : " + dataModal.getAhmedabad(), Toast.LENGTH_SHORT).show();
            }
        });
        return listitemView;
    }
}
