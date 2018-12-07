package com.example.joonas.ht;

import java.util.ArrayList;

public class Bank {

    static ArrayList<User> users = new ArrayList<User>();


    private static final Bank ourInstance = new Bank();
    public static Bank getInstance() {
        return ourInstance;
    }
    private Bank() {

        //lisätään testikäyttäjä
        User user = new User("joonas");
        addUser(user);

    }

    public void addUser(User user) {
        users.add(user);
    }

    public static User getUser(String username) {
        for (int i=0; i<users.size(); i++) {
            if ( users.get(i).userName.equals(username) ) {
                return users.get(i);
            }
        }
        return null;
    }

}
