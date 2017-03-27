package com.example.nicolas.narau;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.eqot.fontawesome.FontAwesome;

import mehdi.sakout.fancybuttons.FancyButton;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.bgred));
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FontAwesome.applyToAllViews(this, findViewById(R.id.loginmain));
        TextView txt = (TextView) findViewById(R.id.pitch2);
        Typewriter narau = (Typewriter) findViewById(R.id.narau);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Roman.otf");
        Typeface ltm = Typeface.createFromAsset(getAssets(), "fonts/LieToMe.otf");
        txt.setTypeface(font);
        narau.setTypeface(ltm);

        narau.setCharacterDelay(250);
        narau.animateText("Narau");



        FancyButton facebookLoginBtn = (FancyButton) findViewById(R.id.btn_fb);
        facebookLoginBtn.setText("Entra con Facebook");
        facebookLoginBtn.setBackgroundColor(Color.parseColor("#3b5998"));
        facebookLoginBtn.setFocusBackgroundColor(Color.parseColor("#5474b8"));
        facebookLoginBtn.setTextSize(17);
        facebookLoginBtn.setRadius(5);
        facebookLoginBtn.setIconResource("\uf082");
        facebookLoginBtn.setIconPosition(FancyButton.POSITION_LEFT);
        facebookLoginBtn.setFontIconSize(30);

        facebookLoginBtn.setOnClickListener(new View.OnClickListener (){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }



}

