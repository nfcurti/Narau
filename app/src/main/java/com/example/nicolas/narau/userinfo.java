package com.example.nicolas.narau;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eqot.fontawesome.FontAwesome;

public class userinfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);

        FontAwesome.applyToAllViews(this, findViewById(R.id.main));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        TextView title = (TextView) findViewById(R.id.title);
        Typeface ltm = Typeface.createFromAsset(getAssets(), "fonts/LieToMe.otf");
        title.setTypeface(ltm);

        LinearLayout reviewdar = (LinearLayout) findViewById(R.id.dar);

  reviewdar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable r = new Runnable() {
                    @Override
                    public void run(){
                        showChangeLangDialog();
                    }
                };
                Handler h = new Handler();
                h.postDelayed(r, 100);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.reviewdar, null);
        dialogBuilder.setView(dialogView);
        AlertDialog b = dialogBuilder.create();
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        b.show();
    }

    public void showVerLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.reviewleer, null);
        dialogBuilder.setView(dialogView);
        ViewPager viewPager = (ViewPager) dialogView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyPagerAdapter(this));
        AlertDialog v = dialogBuilder.create();
        v.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        v.show();
    }

    public void darreview(View view){
        showChangeLangDialog();
    }

    public void verreview(View view){
        showVerLangDialog();
    }
}


