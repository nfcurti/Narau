package com.example.nicolas.narau;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.ContextThemeWrapper;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rowitem = (RelativeLayout) findViewById(R.id.itemrow);


        View prompts = getLayoutInflater().inflate(R.layout.prompts, null);
        Spinner spinner = (Spinner) prompts.findViewById(R.id.profspinner);
        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(this ,R.array.prof_array, android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    View mView = getLayoutInflater().inflate(R.layout.prompts, null);
                    AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog));
                builder.setView(mView);
                builder.setTitle( Html.fromHtml("<font color='#000000'>Rellena los campos</font>"));
                final EditText InputTopic = (EditText) mView.findViewById(R.id.topicinput);
                builder
                            .setCancelable(false)
                            .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogBox, int id) {
                                    Toast.makeText(getApplicationContext(), "Ahora apareces en la lista de profesores", Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("Cancelar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialogBox, int id) {
                                            dialogBox.cancel();
                                        }
                                    });


                    AlertDialog alertDialogAndroid = builder.create();
                    alertDialogAndroid.show();

            }


        });




        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);


        TextView title = (TextView) findViewById(R.id.title);
        Typeface ltm = Typeface.createFromAsset(getAssets(), "fonts/LieToMe.otf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface robotobold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        title.setTypeface(ltm);
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
                //  query
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    public void userinfo(View v) {
        Intent intent = new Intent(MainActivity.this, userinfo.class);
        startActivity(intent);
    }
}
