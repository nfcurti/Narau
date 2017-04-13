package com.example.nicolas.narau;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eqot.fontawesome.FontAwesome;
import com.example.nicolas.narau.Model.MyRecyclerViewAdapter;
import com.example.nicolas.narau.Model.User;
import com.example.nicolas.narau.Model.review;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mehdi.sakout.fancybuttons.FancyButton;

public class userinfo extends AppCompatActivity {


    public ArrayList<review> arrayReview;
    String query;
    public String tvname;
    public String tvinfo;
    public String descr;
    public String rubro;
    public MyPagerAdapter adaptervp;
    TextViewRobotoBold tvnamefinal;
    TextViewRoboto tvtypefinal;
    TextViewRoboto tvrubrofinal;
    TextViewRoboto infofinal;
    ImageView imgfinal;
    public EditText Text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayReview = new ArrayList<>();

        setContentView(R.layout.activity_userinfo);

        tvnamefinal = (TextViewRobotoBold) findViewById(R.id.tvname);
        tvtypefinal = (TextViewRoboto) findViewById(R.id.info);
        tvrubrofinal = (TextViewRoboto) findViewById(R.id.tvrubro);
         infofinal= (TextViewRoboto) findViewById(R.id.tvtype);
        imgfinal = (ImageView) findViewById(R.id.profileimage);

        doBringInfo();

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

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo( searchManager.getSearchableInfo(getComponentName()) );
        query = searchView.getQuery().toString();

        return true;
    }


    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.reviewdar, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
        b.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        FancyButton Send = (FancyButton) dialogView.findViewById(R.id.btn_spotify);
        Text = (EditText) dialogView.findViewById(R.id.reviewdada);

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doDarReview();
                b.hide();
                Toast.makeText(userinfo.this, "Review Enviada!", Toast.LENGTH_LONG);
            }
        });

        b.show();
    }

    public void showVerLangDialog() {
        doBringReview();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.reviewleer, null);
        dialogBuilder.setView(dialogView);

        adaptervp = new MyPagerAdapter(this, arrayReview);
        ViewPager viewPager = (ViewPager) dialogView.findViewById(R.id.viewpager);
        viewPager.setAdapter(adaptervp);

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

    private void doBringInfo(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:3000/prof/" + getIntent().getExtras().getInt("profid");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJSON = new JSONObject(response);

                                System.out.print(responseJSON.getJSONArray("users").getJSONObject(0).getString("name"));
                                tvnamefinal.setText(responseJSON.getJSONArray("users").getJSONObject(0).getString("name"));
                                infofinal.setText(responseJSON.getJSONArray("users").getJSONObject(0).getString("info"));
                                tvrubrofinal.setText(responseJSON.getJSONArray("users").getJSONObject(0).getString("rubro"));
                                tvtypefinal.setText(responseJSON.getJSONArray("users").getJSONObject(0).getString("descr"));

                            String urli = new String(responseJSON.getJSONArray("users").getJSONObject(0).getString("img"));
                            Picasso.with(getApplicationContext()).load(urli).into(imgfinal);

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
                        Toast.makeText(userinfo.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(stringRequest);
    }

    private void doDarReview(){
        RequestQueue queue = Volley.newRequestQueue(this);

        SharedPreferences prefs = getSharedPreferences("id", MODE_PRIVATE);
        int id = prefs.getInt("id", 0);

        String url = "http://192.168.1.7:3000/review/"+id+"/"+getIntent().getExtras().getInt("profid");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJSON = new JSONObject(response);
                            int status = Integer.parseInt(responseJSON.getString("error"));
                            if (status == 0) {
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
                        Toast.makeText(userinfo.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("text", Text.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void doBringReview(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://192.168.1.7:3000/review/"+getIntent().getExtras().getInt("profid");
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        try {
                            if (response.has("review")) {
                                JSONArray reviewArray = response.getJSONArray("review");
                                arrayReview.clear();
                                for (int i=0;i<reviewArray.length();i++) {
                                    arrayReview.add(new review(reviewArray.getJSONObject(i)));
                                }
                                System.out.print(arrayReview);
                                adaptervp.notifyDataSetChanged();
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


}


