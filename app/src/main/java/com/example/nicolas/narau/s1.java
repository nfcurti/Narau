package com.example.nicolas.narau;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link s1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link s1#newInstance} factory method to
 * create an instance of this fragment.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class s1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_s1, container, false);
        TextViewRobotoBold tv1 = (TextViewRobotoBold) view.findViewById(R.id.tv1) ;
        TextViewRoboto tv2 = (TextViewRoboto) view.findViewById(R.id.tv2) ;
        ImageView frame = (ImageView) view.findViewById(R.id.frame);
        Picasso.with(getActivity().getApplicationContext())
                .load("https://raw.githubusercontent.com/nfcurti/Narau/master/app/src/main/res/drawable/is1.png")
                .resize(800, 450)
                .centerInside()
                .into(frame);


        return view;

    }
}