package com.example.nicolas.narau;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.narau.Model.User;
import com.example.nicolas.narau.Model.review;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyPagerAdapter extends PagerAdapter {

    private ArrayList<review> mData = new ArrayList<>();
    private Context mContext;

    public MyPagerAdapter(Context context, ArrayList<review> data) {
        mContext = context;
        this.mData = data;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.review, collection, false);
        TextView review = (TextView) layout.findViewById(R.id.reviewtext);
        ImageView senderpic = (ImageView) layout.findViewById(R.id.senderpic);
        review.setText(mData.get(position).getReviewtext());
        Picasso.with(mContext).load(mData.get(position).getImgurl()).into(senderpic);


        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        if(mData != null) {
            return mData.size();
        }else{
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ModelObject customPagerEnum = ModelObject.values()[position];
        return mContext.getString(customPagerEnum.getTitleResId());
    }

}
