package com.narau.app.main.Slides;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.narau.app.main.R;
import com.narau.app.main.TextViewRoboto;
import com.narau.app.main.TextViewRobotoBold;
import com.squareup.picasso.Picasso;



public class s5 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_s1, container, false);
        TextViewRobotoBold tv1 = (TextViewRobotoBold) view.findViewById(R.id.tv1) ;
        tv1.setText("Menu");
        TextViewRoboto tv2 = (TextViewRoboto) view.findViewById(R.id.tv2) ;
        tv2.setText("Desliza hacia la derecha para ver el menu.");
        ImageView frame = (ImageView) view.findViewById(R.id.frame);
        Picasso.with(getActivity().getApplicationContext())
                .load("https://raw.githubusercontent.com/nfcurti/Narau/master/app/src/main/res/drawable/is5.png")
                .resize(900, 500)
                .centerInside()
                .into(frame);


        return view;

    }
}