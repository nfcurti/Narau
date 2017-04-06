package com.example.juancurti.foxie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.juancurti.foxie.Adapters.CustomAdapter;
import com.example.juancurti.foxie.Model.Message;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static CustomAdapter adapter;
    ArrayList<Message> dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lvMessages = (ListView)findViewById(R.id.listMessages);
        final Button btnSend = (Button)findViewById(R.id.buttonSend);
        final EditText etText = (EditText)findViewById(R.id.etText);
        lvMessages.setDivider(null);
        lvMessages.setDividerHeight(0);
        dataModels = new ArrayList<>();
        dataModels.add(new Message(1, "Juan", "Hola ñero", "10:14"));
        dataModels.add(new Message(1, "Juan", "todo bien?", "10:14"));
        dataModels.add(new Message(2, "Nico", "Si, puto", "10:16"));

        adapter= new CustomAdapter(dataModels,getApplicationContext());

        lvMessages.setAdapter(adapter);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etText.getText().toString().isEmpty()){
                    Message thisMessage = new Message(1, "Juan", etText.getText().toString(), "00:00");
                    dataModels.add(thisMessage);

                    etText.setText("");
                    adapter.notifyDataSetChanged();

                    lvMessages.setSelection(dataModels.size() - 1);
                }
            }
        });
    }

}
