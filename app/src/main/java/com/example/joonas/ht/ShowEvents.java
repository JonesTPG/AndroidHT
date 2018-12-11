package com.example.joonas.ht;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class ShowEvents extends AppCompatActivity {

    ArrayList<Event> eventList;

    RecyclerView recyclerView;
    Spinner spinneri;
    ArrayList<String> accountList;

    String selectedAccount;

    Button saveEvents;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_events);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.eventList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        spinneri = findViewById(R.id.accountSpinner);
        saveEvents = findViewById(R.id.save_events);


        accountList = Bank.getUser(Current.currentUser).getAccountNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountList);
        spinneri.setAdapter(adapter);

        spinneri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedAccount = selectedItem;
                // do your stuff

                eventList = Bank.getUser(Current.currentUser).getAccount(selectedAccount).getEvents();

                EventAdapter adapteri = new EventAdapter(parent.getContext(), eventList);
                recyclerView.setAdapter(adapteri);

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void saveEvents(View v) {

        if (eventList == null || eventList.size() == 0) {
            return;
        }
        String json = SaveEvents.getEventsAsJson(eventList);

        String filename = selectedAccount + "-events";

        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        startActivity(new Intent(ShowEvents.this, UserMain.class));

        return;
    }
}
