package com.example.joonas.ht;

import java.util.Date;

public class Event {

    Date date;
    String type;
    String from;
    String to;
    int amount;

    public Event(Date date, String type, String from, String to, int amount) {
        this.date = date;
        this.type = type;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }


}
