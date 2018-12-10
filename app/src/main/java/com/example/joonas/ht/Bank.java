package com.example.joonas.ht;

import java.util.ArrayList;
import java.util.Date;

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

    public static int transferMoney(String fromId, String toId, int amount) {

        Account from = getUser(Current.currentUser).getAccount(fromId);
        Account to = getUser(Current.currentUser).getAccount(toId);

        if ( from.getBalance()+from.getCreditLimit() < amount ) {
            System.out.println("From-tilillä ei ole tarpeeksi rahaa.");
            return -1;
        }
        else {

            from.withDraw(amount);
            to.deposit(amount);

            Date date = new Date();
            String type = "transfer";
            from.addEvent(new Event(date, type, fromId, toId, amount));
            to.addEvent(new Event(date, type, fromId, toId, amount));

            System.out.println("Tilisiirto suoritettu.");
            return 1;
        }



    }

}
