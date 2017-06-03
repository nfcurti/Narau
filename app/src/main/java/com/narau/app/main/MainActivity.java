package com.narau.app.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.ImageView;
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
import com.narau.app.main.Model.MyRecyclerViewAdapter;
import com.narau.app.main.Model.User;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

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
    public String loginImg;
    public String loginName;
    MyRecyclerViewAdapter adapter;
    public ArrayList<User> arrayUsers;
    public String query;
    public SearchView searchView;
    public String msisdn;
    private static boolean RUN_ONCE = true;
    final String PREFS_NAME = "isfirst";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        loginId = sharedPref.getInt("loginId", 0);
        loginImg = sharedPref.getString("img", "null");
        loginName = sharedPref.getString("name", "null");
        msisdn = sharedPref.getString("msisdn", "null");
        if(msisdn.equals("desconocido")){
            asknumber();
        }else
        {System.out.println("Logged in");}


        isprof();
        View View = getLayoutInflater().inflate(R.layout.header, null);
        final ImageView imgfinal = (ImageView) View.findViewById(R.id.profileimage);
        final TextViewRoboto tvnamefinal = (TextViewRoboto) View.findViewById(R.id.tvname);
        arrayUsers = new ArrayList<>();
        setcards();
        setNavigationDrawer();
        SharedPreferences.Editor editor = getSharedPreferences("id", MODE_PRIVATE).edit();
        editor.putInt("id", loginId);
        editor.putString("msisdn", msisdn);
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
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final FloatingActionButton anotherfab = (FloatingActionButton) findViewById(R.id.anotherfab);
        fab.setVisibility(View.VISIBLE );
        anotherfab.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View prompts = getLayoutInflater().inflate(R.layout.prompts, null);
                View mView = getLayoutInflater().inflate(R.layout.prompts, null);
                fab.setVisibility(View.INVISIBLE );
                anotherfab.setVisibility(View.VISIBLE);

                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog));
                builder.setView(mView);
                InputTopic = (EditText) mView.findViewById(R.id.topicinput);
                InputDesc = (EditText) mView.findViewById(R.id.descinput);

                final Spinner spinner = (Spinner) mView.findViewById(R.id.profspinner);

                ArrayList<String> rubros = new ArrayList<String>();
                rubros.add("Academico");
                rubros.add("Deportes");
                rubros.add("Musica");
                rubros.add("Idiomas");

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, rubros);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        InputRubro=spinner.getItemAtPosition(position).toString();
                        System.out.println(InputRubro);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });




                builder.setTitle( Html.fromHtml("<font color='#000000'>Rellena los campos</font>"));

                builder
                        .setCancelable(false)
                        .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                                if(InputDesc.getText().toString().trim().length() == 0){
                                    Toast.makeText(getApplicationContext(), "El campo descripcion esta vacio", Toast.LENGTH_LONG).show();
                                    fab.setVisibility(View.VISIBLE );
                                    anotherfab.setVisibility(View.INVISIBLE);
                                }
                                else if(InputTopic.getText().toString().trim().length() == 0){
                                    Toast.makeText(getApplicationContext(), "El campo topico esta vacio", Toast.LENGTH_LONG).show();
                                    fab.setVisibility(View.VISIBLE );
                                    anotherfab.setVisibility(View.INVISIBLE);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Ahora apareces como profesor", Toast.LENGTH_LONG).show();
                                    doBeProf();
                                }

                            }
                        })
                        .setNegativeButton("Cancelar",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        fab.setVisibility(View.VISIBLE );
                                        anotherfab.setVisibility(View.INVISIBLE);
                                        dialogBox.cancel();
                                    }
                                });


                AlertDialog alertDialogAndroid = builder.create();
                alertDialogAndroid.show();


            }


        });

        anotherfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.myDialog));
                builder
                        .setTitle("Eliminar Perfil de Profesor?")
                        .setMessage("Solo eliminaras tu perfil de profesor, pero podras volver a crearlo otra vez.")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                doNotAnymore();
                                fab.setVisibility(View.VISIBLE );
                                anotherfab.setVisibility(View.GONE);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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

        return super.onOptionsItemSelected(item);
    }

    public void userinfo(View v) {
        Intent intent = new Intent(MainActivity.this, userinfo.class);
        startActivity(intent);
    }

    private void doBeProf(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://45.55.135.115/user/"+loginId;
        System.out.print(url);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJSON = new JSONObject(response);
                            int status = Integer.parseInt(responseJSON.getString("error"));
                            if (status == 0) {
                                String var = new String(responseJSON.getString("users"));
                                randomprof();
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

    private void doNotAnymore(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://45.55.135.115/prof/"+loginId;
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        arrayUsers.clear();
                        Toast.makeText(MainActivity.this,"Ya no apareces como profesor", Toast.LENGTH_LONG).show();
                        randomprof();
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        queue.add(stringRequest);
    }

    public void randomprof(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "http://45.55.135.115/user/random";
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
        final String url = "http://45.55.135.115/search/"+query;
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
        DrawerLayout layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        if (layout.isDrawerOpen(GravityCompat.START)) {
            layout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            moveTaskToBack(true);
        }

    }

    private void setNavigationDrawer() {
        final DrawerLayout dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);

        View view = navView.inflateHeaderView(R.layout.header);

        ImageView img = (ImageView) view.findViewById(R.id.profileimage);
        TextViewRoboto tvr = (TextViewRoboto) view.findViewById(R.id.tvname);
        tvr.setText("Hola, "+loginName.substring(0, loginName.indexOf(" "))+"!");

        Picasso.with(getApplicationContext()).load(loginImg).into(img);




        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.logout) {
                    dLayout.closeDrawers();
                    LoginManager.getInstance().logOut();
                    Intent i = new Intent(MainActivity.this, Login.class);
                    startActivity(i);
                }

                if (itemId == R.id.academico) {
                    dLayout.closeDrawers();
                    query = "academico";
                    search();
                }
                if (itemId == R.id.sports) {
                    dLayout.closeDrawers();
                    query = "Deportes";
                    search();
                }
                if (itemId == R.id.Music) {
                    dLayout.closeDrawers();
                    query = "Musica";
                    search();
                }
                if (itemId == R.id.Languages) {
                    dLayout.closeDrawers();
                    query = "Idiomas";
                    search();
                }
                if (itemId == R.id.help) {
                    Intent i = new Intent(MainActivity.this, Splash2.class);
                    startActivity(i);
                }

                if (itemId == R.id.namba) {
                    asknumber();
                }

                return false;
            }
        });
    }

    private void asknumber(){

        new LovelyTextInputDialog(this, R.style.TintTheme)
                .setTopColorRes(R.color.bgred)
                .setTitle("Ingresa tu numero:  ")
                .setMessage("Tu numero es "+msisdn+", asegurate que sea correcto o no podras ser contactado!").setHint("Ej: 66601468")
                .setIcon(R.drawable.ic_help_outline_white_24dp)
                .setInputFilter("Asegurate de estar ingresando un numero de 8 digitos!", new LovelyTextInputDialog.TextFilter() {
                    @Override
                    public boolean check(String text) {
                        return text.matches("^\\d{8}$");
                    }
                })
                .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                    @Override
                    public void onTextInputConfirmed(String text) {
                        msisdn=text;
                        doasknumber();
                    }
                })
                .show();
    }

    private void doasknumber(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://45.55.135.115/user/"+loginId+"/msisdn";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJSON = new JSONObject(response);
                            int status = Integer.parseInt(responseJSON.getString("error"));
                            if (status == 0) {
                                String var = new String(responseJSON.getString("users"));
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
                params.put("msisdn", msisdn);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void isprof(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://45.55.135.115/prof/" + loginId;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject responseJSON = new JSONObject(response);
                            final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                            final FloatingActionButton anotherfab = (FloatingActionButton) findViewById(R.id.anotherfab);
                            if(responseJSON.getJSONArray("users").length()>0)
                            {
                                fab.setVisibility(View.INVISIBLE );
                                anotherfab.setVisibility(View.VISIBLE);
                            }
                            else{}

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

                    }
                });
        queue.add(stringRequest);
    }
}
