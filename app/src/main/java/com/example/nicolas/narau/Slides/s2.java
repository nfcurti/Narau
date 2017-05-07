package com.example.nicolas.narau.Slides;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nicolas.narau.R;
import com.example.nicolas.narau.TextViewRoboto;
import com.example.nicolas.narau.TextViewRobotoBold;
import com.squareup.picasso.Picasso;

public class s2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_s1, container, false);
        TextViewRobotoBold tv1 = (TextViewRobotoBold) view.findViewById(R.id.tv1) ;
        tv1.setText("Eres profesor?");
        TextViewRoboto tv2 = (TextViewRoboto) view.findViewById(R.id.tv2) ;
        tv2.setText("Llena los campos explicando quien eres y que enseñas, y la gente podra buscarte.");
        ImageView frame = (ImageView) view.findViewById(R.id.frame);
        Picasso.with(getActivity().getApplicationContext())
                .load("https://raw.githubusercontent.com/nfcurti/Narau/master/app/src/main/res/drawable/is2.gif")
                .resize(900, 500)
                .centerInside()
                .into(frame);


        return view;

    }
}