package com.example.nicolas.narau.Model;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicolas.narau.MainActivity;
import com.example.nicolas.narau.R;
import com.example.nicolas.narau.userinfo;
import com.squareup.picasso.Picasso;

import java.io.Console;
import java.util.ArrayList;

/**
 * Created by Nicolas on 2/4/2017.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<User> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
        public MyRecyclerViewAdapter(Context context, ArrayList<User> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.infomain, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // binds the data to the textview in each cell
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.myTextView.setText(mData.get(position).getName());
        holder.rubro.setText(mData.get(position).getRubro());

        String imageUri = mData.get(position).getimg();

        Picasso.with(context).load(imageUri).into(holder.image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, userinfo.class);
                i.putExtra("profid", mData.get(position).getIdprof());
                i.putExtra("idprof", mData.get(position).getId());
                view.getContext().startActivity(i);
            }
        });

    }

    // total number of cells
    @Override
    public int getItemCount() {
        if(mData != null) {
            return mData.size();
        }else{
            return 0;
        }
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView myTextView;
        public TextView rubro;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            image = (ImageView) itemView.findViewById(R.id.profileimage);
            myTextView = (TextView) itemView.findViewById(R.id.name);
            rubro = (TextView) itemView.findViewById(R.id.loc);
            itemView.setOnClickListener(this);


        }



        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

        }
    }

    // convenience method for getting data at click position
    public User getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);


    }


}

