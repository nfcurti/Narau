package com.example.juancurti.foxie.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juancurti.foxie.Model.Message;
import com.example.juancurti.foxie.R;

import java.util.ArrayList;

/**
 * Created by juancurti on 2/25/17.
 */

public class CustomAdapter  extends ArrayAdapter<Message> implements View.OnClickListener {
    private ArrayList<Message> dataSet;
    Context mContext;

    private static class ViewHolder {
        LinearLayout llContainer;
        TextView tvUsername;
        TextView tvMessage;
        TextView tvDate;
    }

    public CustomAdapter(ArrayList<Message> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public void onClick(View v) {

    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Message dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.tvMessage = (TextView) convertView.findViewById(R.id.tvMessage);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.tvDate);
            viewHolder.llContainer = (LinearLayout)convertView.findViewById(R.id.llContainer);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }


        lastPosition = position;

        viewHolder.tvUsername.setText(dataModel.getUsername());
        viewHolder.tvMessage.setText(dataModel.getMessage());
        viewHolder.tvDate.setText(dataModel.getDate());
        if(dataModel.getId()!=1){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)viewHolder.llContainer.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_END);
            viewHolder.llContainer.setLayoutParams(params);
            viewHolder.tvUsername.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            viewHolder.tvMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            viewHolder.tvDate.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }else{
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)viewHolder.llContainer.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_PARENT_START);
            viewHolder.llContainer.setLayoutParams(params);
            viewHolder.tvUsername.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            viewHolder.tvMessage.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            viewHolder.tvDate.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }


        // Return the completed view to render on screen
        return convertView;
    }
}
