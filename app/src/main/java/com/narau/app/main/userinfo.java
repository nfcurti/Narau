package com.narau.app.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
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
import com.narau.app.main.Model.review;
import com.squareup.picasso.Picasso;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


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
    public String Text;
    public String number;
    public int largo;
    public View dialogView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayReview = new ArrayList<>();
        setContentView(R.layout.activity_userinfo);
        final  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.faba);
        tvtypefinal = (TextViewRoboto) findViewById(R.id.info);
        tvrubrofinal = (TextViewRoboto) findViewById(R.id.tvrubro);
        infofinal= (TextViewRoboto) findViewById(R.id.tvtype);
        imgfinal = (ImageView) findViewById(R.id.profileimage);
        tvnamefinal = (TextViewRobotoBold) findViewById(R.id.tvname);

        final LinearLayout main1 = (LinearLayout) findViewById(R.id.main1);
        new CountDownTimer(500,1000){
            @Override
            public void onTick(long millisUntilFinished){}

            @Override
            public void onFinish(){
                main1.setVisibility(View.VISIBLE);
                fab.setVisibility(View.VISIBLE);
            }
        }.start();

        doBringInfo();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("smsto:" + number);
                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                i.setPackage("com.whatsapp");
                startActivity(Intent.createChooser(i, ""));
            }
        });
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

        return true;
    }

    private void showChangeLangDialog() {
        new LovelyTextInputDialog(this, R.style.TintTheme)
                .setTopColorRes(R.color.vimeo)
                .setTitle("Ingresa tu review: ")
                .setMessage("\n Que le parecio el profesor?" +
                        " \n Fue puntual?" +
                        " \n Le gusto su manera de trabajar?" +
                        " \n Lo recomendarias?")
                .setIcon(R.drawable.ic_thumbs_up_down_white_24dp)
                .setInputFilter("Asegurate de estar ingresando algo!", new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        Text=text;
                        return text.matches(".+");
                    }
                })
                .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String text) {
                        doDarReview();

                    }
                })
                .show();

    }

    public void showVerLangDialog() {
        doBringReview();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        dialogView = inflater.inflate(R.layout.reviewleer, null);
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
        hasreview();
    }

    private void doBringInfo(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://45.55.135.115/prof/" + getIntent().getExtras().getInt("idprof");
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
                                number=(responseJSON.getJSONArray("users").getJSONObject(0).getString("msisdn"));
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
        if(id != getIntent().getExtras().getInt("idprof")){
        String url = "http://45.55.135.115/review/"+id+"/"+getIntent().getExtras().getInt("idprof");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJSON = new JSONObject(response);
                            int status = Integer.parseInt(responseJSON.getString("error"));
                            if (status == 0) {
                                Toast.makeText(userinfo.this, "Review Enviada!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(userinfo.this, "No se pudo enviar!", Toast.LENGTH_LONG).show();
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
                params.put("text", Text.toString());
                return params;
            }
        };
        queue.add(stringRequest);}
        else{
            Toast.makeText(userinfo.this, "No podes hacerte una review a vos mismo!", Toast.LENGTH_LONG).show();
        }
    }

    public void doBringReview(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://45.55.135.115/review/"+getIntent().getExtras().getInt("idprof");
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

                                for (int i=0;i<reviewArray.length();i++) {
                                    arrayReview.add(new review(reviewArray.getJSONObject(i)));
                                }
                                System.out.print(arrayReview);

                                largo = arrayReview.size();

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

    public void hasreview(){

    }
}


