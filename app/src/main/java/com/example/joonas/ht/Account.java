package com.example.joonas.ht;

import java.util.ArrayList;


public abstract class Account {

    protected int balance;
    protected String id;
    protected int creditLimit;
    protected boolean canBeUsed;


    ArrayList<Event> events = new ArrayList<Event>();




    public void withdDraw(int amount) {
        int newBalance = this.balance-amount;
        if ( newBalance < 0 ) {
            return;
        }
        else {
            this.balance = newBalance;
        }
    }

    public void deposit(int amount) {
        this.balance = this.balance+amount;
    }



}

class SavingAccount extends Account {

    public SavingAccount(int balance, String accountId) {
        this.balance = balance;
        this.id = accountId;
        this.canBeUsed = false;
    }

}

class CreditAccount extends Account {

    public CreditAccount(int balance, String accountId, int creditLimit) {
        this.balance = balance;
        this.id = accountId;
        this.creditLimit = creditLimit;
        this.canBeUsed = true;
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
}




