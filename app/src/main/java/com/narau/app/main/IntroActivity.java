package com.narau.app.main;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.narau.app.main.Slides.s1;
import com.narau.app.main.Slides.s2;
import com.narau.app.main.Slides.s3;
import com.narau.app.main.Slides.s4;
import com.narau.app.main.Slides.s5;
import com.github.paolorotolo.appintro.AppIntro2;

public class IntroActivity extends AppIntro2 {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        s1 s1= new s1();
        s2 s2= new s2();
        s3 s3= new s3();
        s4 s4= new s4();
        s5 s5= new s5();

        addSlide(s1);
        addSlide(s2);
        addSlide(s3);
        addSlide(s4);
        addSlide(s5);
        showSkipButton(true);
        setProgressButtonEnabled(true);
        setFadeAnimation();

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        Intent i = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        Intent i = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}
