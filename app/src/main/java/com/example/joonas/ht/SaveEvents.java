package com.example.joonas.ht;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class SaveEvents {
    private static final SaveEvents ourInstance = new SaveEvents();

    public static SaveEvents getInstance() {
        return ourInstance;
    }

    private SaveEvents() {

    }

    public static String getEventsAsJson(ArrayList<Event> events) {

        String fullJson = "";
        Gson gson = new Gson();
        for (int i=0; i<events.size(); i++) {
            String json = gson.toJson(events.get(i));
            fullJson = fullJson + json + "\n";

        }
        return fullJson;

    }
}
