package com.narau.app.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.narau.app.main.Model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.eqot.fontawesome.FontAwesome;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    public CallbackManager callbackManager;
    final String PREFS_NAME = "isfirst";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        validatesdk();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        super.onCreate(savedInstanceState);
        checklogin(AccessToken.getCurrentAccessToken());
        setContentView(R.layout.activity_login);
        typefacesandfont();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setText("Entrar con Facebook");
        loginButton.setReadPermissions("email");

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

                if (settings.getBoolean("my_first_time", true)) {
                    Intent i = new Intent(Login.this, IntroActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    settings.edit().putBoolean("my_first_time", false).apply();

                }else
                {
                    settings.edit().putBoolean("my_first_time", false).apply();
                    Intent i = new Intent(Login.this, Splash.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                }
                dologin();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.print("There was an error");
            }
        });
    }

    public void typefacesandfont(){
        FontAwesome.applyToAllViews(this, findViewById(R.id.loginmain));
        TextView txt = (TextView) findViewById(R.id.pitch2);
        Typewriter narau = (Typewriter) findViewById(R.id.narau);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/AvenirLTStd-Roman.otf");
        Typeface ltm = Typeface.createFromAsset(getAssets(), "fonts/LieToMe.otf");
        txt.setTypeface(font);
        narau.setTypeface(ltm);
        narau.setCharacterDelay(250);
        narau.animateText("Narau");

    }

    public void validatesdk(){
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.bgred));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void checklogin(AccessToken currentAccessToken) {

        if (currentAccessToken != null) {
            Intent i = new Intent(Login.this, MainActivity.class);
            startActivity(i);
            dologin();
            finish();

        } else {

        }
    }

    private void dologin(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://45.55.135.115/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJSON = new JSONObject(response);
                            System.out.print(responseJSON);
                            int status = Integer.parseInt(responseJSON.getString("error"));
                            if (status == 0) {
                                final User thisUser = new User(responseJSON.getJSONObject("userfb"));

                                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(Login.this);
                                SharedPreferences.Editor editor = sharedPref.edit();

                                //Bundle bundle = new Bundle();
                                editor.putInt("loginId", thisUser.getId());
                                editor.putString("img", thisUser.getimg());
                                editor.putString("name", thisUser.getName());
                                editor.putString("msisdn", thisUser.getMsisdn());
                                editor.commit();
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
                        //error.toString() to standrad error msg
                        Toast.makeText(Login.this,"No se pudo conectar con el servidor", Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("token",AccessToken.getCurrentAccessToken().getToken());
                return params;
            }
        };
        int socketTimeout = 60000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        queue.add(stringRequest);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        moveTaskToBack(true);

    }

}

