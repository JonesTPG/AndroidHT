package com.example.joonas.ht;

import java.util.Date;

/*class that defines the Event-object. Constructor takes all the neccessary parameters.
* This object is usually created in the Bank class when some action has been done to accounts*/
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

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getAmount() {
        return amount;
    }
}
