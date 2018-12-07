package com.example.joonas.ht;

import java.util.ArrayList;

public class Bank {

    ArrayList<User> users = new ArrayList<User>();

    private static final Bank ourInstance = new Bank();

    public static Bank getInstance() {
        return ourInstance;
    }

    private Bank() {

    }
}
