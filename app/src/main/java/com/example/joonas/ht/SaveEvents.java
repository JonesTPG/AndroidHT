package com.example.joonas.ht;

import java.util.ArrayList;

public class SaveEvents {
    private static final SaveEvents ourInstance = new SaveEvents();

    public static SaveEvents getInstance() {
        return ourInstance;
    }

    private SaveEvents() {

    }

    public void save(ArrayList<Event> events) {

    }
}
