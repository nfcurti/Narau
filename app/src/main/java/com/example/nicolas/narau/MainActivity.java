package com.example.nicolas.narau;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.support.v7.widget.SearchView;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nicolas.narau.Model.MyRecyclerViewAdapter;
import com.example.nicolas.narau.Model.User;
import com.facebook.login.LoginManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener{
    public EditText InputTopic;
    public EditText InputDesc;
    public String InputRubro;
    public int loginId;
    MyRecyclerViewAdapter adapter;
    public ArrayList<User> arrayUsers;
    public String query;
    public SearchView searchView;
    private static boolean RUN_ONCE = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayUsers = new ArrayList<>();
        setcards();
        loginId = getIntent().getIntExtra("loginId", 0);


        SharedPreferences.Editor editor = getSharedPreferences("id", MODE_PRIVATE).edit();
        editor.putInt("id", loginId);
        editor.commit();

        fab();
        SupportActionBar();
        randomprof();


        TextView title = (TextView) findViewById(R.id.title);
        Typeface ltm = Typeface.createFromAsset(getAssets(), "fonts/LieToMe.otf");
        Typeface roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface robotobold = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Bold.ttf");
        title.setTypeface(ltm);

        final SwipeRefreshLayout refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);

        refresh.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        randomprof();
                        refresh.setRefreshing(false);

                    }
                }
        );


    }

    public void setcards(){
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvNumbers);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        adapter = new MyRecyclerViewAdapter(this, arrayUsers);
        recyclerView.setAdapter(adapter);
    }

    public void fab(){
        //BUILDER DEL FAB
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View prompts = getLayoutInflater().inflate(R.layout.prompts, null);
                Spinner spinner = (Spinner) prompts.findViewById(R.id.profspinner);
                ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this ,R.array.prof_array, android.R.layout.simple_list_item_1);
                spinner.setAdapter(adapter);
                View mView = getLayoutInflater().inflate(R.layout.prompts, null);


                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog));
                builder.setView(mView);
                InputTopic = (EditText) mView.findViewById(R.id.topicinput);
                InputDesc = (EditText) mView.findViewById(R.id.descinput);
                InputRubro = spinner.getSelectedItem().toString();
                builder.setTitle( Html.fromHtml("<font color='#000000'>Rellena los campos</font>"));

                builder
                        .setCancelable(false)
                        .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                                Toast.makeText(getApplicationContext(), "Ahora apareces en la lista de profesores", Toast.LENGTH_LONG).show();
                                doBeProf();
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

    }

    public void SupportActionBar(){
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
            searchView = (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String s) {
                //Log.e("onQueryTextChange", "called");
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String s) {

                query = searchView.getQuery().toString();
                Toast.makeText(MainActivity.this, query, Toast.LENGTH_LONG);
                search();
                return false;
            }

        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.logout) {
            LoginManager.getInstance().logOut();
            Intent i = new Intent(MainActivity.this, Login.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void userinfo(View v) {
        Intent intent = new Intent(MainActivity.this, userinfo.class);
        startActivity(intent);
    }

    private void doBeProf(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:3000/user/"+loginId;
        System.out.println(url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJSON = new JSONObject(response);
                            int status = Integer.parseInt(responseJSON.getString("error"));
                            if (status == 0) {
                                String var = new String(responseJSON.getString("users"));
                                System.out.println(var);
                            } else {
                                System.out.println("There may be an errieriwehiewew");
                            }
                        } catch (final JSONException e) {
                            Log.e("ERROR", "Error parsing JSON: " + e.getMessage());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                }
                            });
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("rubro", InputRubro.toString());
                params.put("info", InputTopic.getText().toString());
                params.put("descr", InputDesc.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void randomprof(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://192.168.1.7:3000/user/random";
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        try {
                            if (response.has("users")) {
                                JSONArray usersArray = response.getJSONArray("users");

                                arrayUsers.clear();
                                for (int i=0;i<usersArray.length();i++) {
                                    arrayUsers.add(new User(usersArray.getJSONObject(i)));
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }catch(JSONException e){
                            Log.e("ERROR", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );

        queue.add(getRequest);

    }

    public void search(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://192.168.1.7:3000/search/"+query;
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        try {
                            if (response.has("search")) {
                                JSONArray usersArray = response.getJSONArray("search");

                                arrayUsers.clear();
                                for (int i=0;i<usersArray.length();i++) {
                                    arrayUsers.add(new User(usersArray.getJSONObject(i)));
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }catch(JSONException e){
                            Log.e("ERROR", e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.getMessage());
                    }
                }
        );

        queue.add(getRequest);

    }

    public void onItemClick(View view, int position) {
        Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
    }

    @Override
    public boolean onSearchRequested() {
        Bundle appData = new Bundle();
        appData.putString("data", query);
        return true;
    }

    @Override
    public void onBackPressed(){
        moveTaskToBack(true);
    }

}
