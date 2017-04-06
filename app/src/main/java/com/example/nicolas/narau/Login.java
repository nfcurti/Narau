package com.example.nicolas.narau;

import android.content.Intent;
import android.graphics.Typeface;
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
import com.example.nicolas.narau.Model.User;
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

    public int thisUserId;

    public CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.bgred));
        }
        super.onCreate(savedInstanceState);
        checklogin(AccessToken.getCurrentAccessToken());


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
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

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");



        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                dologin();


            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


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

        } else {

        }
    }

    private void dologin(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.7:3000/login";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject responseJSON = new JSONObject(response);
                        int status = Integer.parseInt(responseJSON.getString("error"));
                        if (status == 0) {
                            final User thisUser = new User(responseJSON.getJSONObject("userfb"));
                            thisUserId = responseJSON.getJSONObject("userfb").getInt("id");
                            Bundle bundle = new Bundle();
                            bundle.putInt("loginId", thisUserId);



                            Intent i = new Intent(Login.this, MainActivity.class);
                            i.putExtras(bundle);

                            startActivity(i);

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
                    Toast.makeText(Login.this,error.toString(), Toast.LENGTH_LONG).show();
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

}

