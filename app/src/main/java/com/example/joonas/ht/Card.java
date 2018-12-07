package com.example.joonas.ht;

public class Card {

    private String id;
    private int withdrawLimit;
    private int amountLimit;
    private String type;

    public Card(String id, int withdrawLimit, int amountLimit, String type) {
        this.id = id;
        this.withdrawLimit = withdrawLimit;
        this.amountLimit = amountLimit;
        this.type = type;
    }
}
