package com.example.joonas.ht;

import java.util.ArrayList;


public class Account {



    protected int balance;
    protected String id;
    protected int creditLimit;
    protected boolean canBeUsed;
    protected String type;
    ArrayList<Event> events;
    ArrayList<Card> cards;

    public Account(int balance, String id, int creditLimit, boolean canBeUsed, String type) {
        this.balance = balance;
        this.id = id;
        this.creditLimit = creditLimit;
        this.canBeUsed = canBeUsed;
        this.type = type;

        events = new ArrayList<Event>();
        cards = new ArrayList<Card>();
    }




    public void setCanBeUsed(boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEventsAmount() {
        return events.size();
    }

    public int getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public boolean isCanBeUsed() {
        return canBeUsed;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public String getType() {
        return type;
    }

    public Card getCard(String cardId) {
        for (int i=0; i<cards.size(); i++) {
            if (cards.get(i).getCardId().equals(cardId)) {
                return cards.get(i);
            }
        }

        return null;
    }

    public boolean withDraw(int amount) {
        int newBalance = this.balance-amount;
        if ( newBalance < -this.creditLimit ) {
            return false;
        }
        else {
            this.balance = newBalance;
            return true;
        }
    }

    public void deposit(int amount) {

        this.balance = this.balance+amount;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getAccountCards() {
        return cards;
    }


}














