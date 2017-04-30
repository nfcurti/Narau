package com.example.nicolas.narau;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class IntroActivity extends AppIntro2 {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();



        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntro2Fragment.newInstance("Pantalla Principal", "Aqui veras perfiles de profesores elegidos al azar y el boton central con el que podras aparecer como profesor.", R.drawable.is1, getResources().getColor(R.color.bgred)));
        addSlide(AppIntro2Fragment.newInstance("Eres profesor?", "Solo deberas llenar los campos explicando quien eres y que enseñas, y la gente podra buscarte.", R.drawable.is2, getResources().getColor(R.color.bgred)));
        addSlide(AppIntro2Fragment.newInstance("Buscas profesor?", "En la barra superior, si clickeas en la pequeña lupa, podras buscar profesores.", R.drawable.is3, getResources().getColor(R.color.bgred)));
        addSlide(AppIntro2Fragment.newInstance("Buscas profesor?", "Clickea en el profesor que te interese y seras llevado a una pagina donde podras leer mas sobre el y dar o leer una review.", R.drawable.is4, getResources().getColor(R.color.bgred)));
        addSlide(AppIntro2Fragment.newInstance("Menu", "Desliza hacia la derecha para desplegar el menu para filtrar profesores por categorias, salir de la aplicacion o volver a ver este tutorial.", R.drawable.is5, getResources().getColor(R.color.bgred)));
        showSkipButton(true);
        setProgressButtonEnabled(true);
        setFlowAnimation()
        ;

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
