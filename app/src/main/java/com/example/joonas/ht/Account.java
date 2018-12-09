package com.example.joonas.ht;

import java.util.ArrayList;


public abstract class Account {



    protected int balance;
    protected String id;
    protected int creditLimit;
    protected boolean canBeUsed;
    protected String type;

    ArrayList<Event> events = new ArrayList<Event>();
    ArrayList<Card> cards = new ArrayList<Card>();


    public void setCanBeUsed(boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean withdDraw(int amount) {
        int newBalance = this.balance-amount;
        if ( newBalance < 0 ) {
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



}

class SavingAccount extends Account {


    public SavingAccount(int balance, String accountId) {
        this.balance = balance;
        this.id = accountId;
        this.canBeUsed = false;
        this.type = "säästötili";
    }

}

class CreditAccount extends Account {


    public CreditAccount(int balance, String accountId, int creditLimit) {
        this.balance = balance;
        this.id = accountId;
        this.creditLimit = creditLimit;
        this.canBeUsed = true;
        this.type = "käyttötili";
    }

    public void withDraw(int amount) {
        int newBalance = this.balance-amount;
        if ( newBalance < -this.creditLimit ) {
            return;
        }
        else {
            this.balance = newBalance;
        }
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public ArrayList<Card> getAccountCards() {
        return cards;
    }
}









