package com.rajendra.vacationtourapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.RecentsData;

public class MainAdapter extends FirebaseRecyclerAdapter<RecentsData,MainAdapter.myViewHolder> {

    public MainAdapter(@NonNull FirebaseRecyclerOptions<RecentsData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull RecentsData model) {
        holder.place_name.setText(model.getPlaceName());
        holder.country_name.setText(model.getCountryName());
        holder.price.setText(model.getPrice());
        Glide.with(holder.place_image.getContext())
                .load(model.getImageUrl())
                .placeholder(R.drawable.ic_action_password)
                .circleCrop()
                .error(R.drawable.ic_action_password)
                .into(holder.place_image);


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recents_row_item,parent,false);
        return new myViewHolder(view);
    }

    static class myViewHolder extends RecyclerView.ViewHolder {
        ImageView place_image;
        TextView place_name, country_name, price;

        public myViewHolder(@NonNull View itemView){
            super(itemView);
            place_image = itemView.findViewById(R.id.place_image);
            place_name = itemView.findViewById(R.id.place_name);
            country_name = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);

        }
    }
}
