package com.example.joonas.ht;

import android.content.Context;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
        //User user = new User("joonas");
        //addUser(user);

    }

    public static void addUser(User user) {
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

    public static int transferMoneyToOther(String fromId, String toId, int amount) {
        //todo: fromId on nykyisen käyttäjän, toId jonkun toisen käyttäjän tili. Molemmat tili-oliot
        //pitäisi saada haltuun ja tehdä sitten rahojen siirto.

        //todo: tarkista myös, että tilit eivät ole saman käyttäjän


        return 1;
    }

    public static int cardPayment(String cardId, int amount) {

        Card card = getUser(Current.currentUser).getCardById(cardId);
        Account account =  getUser(Current.currentUser).getAccount(card.getAccountId());

        if (amount > card.getAmountLimit()) {
            return -2;
        }
        boolean success = account.withDraw(amount);
        if (success) {

            Date date = new Date();
            String type = "korttimaksu";
            String from = card.getCardId();
            String to = "pankki";


            account.addEvent(new Event(date, type, from, to, amount));


            return 1;
        }
        else {
            return -1;
        }

    }

    public static int cardWithdraw(String cardId, int amount) {
        Card card = getUser(Current.currentUser).getCardById(cardId);
        Account account =  getUser(Current.currentUser).getAccount(card.getAccountId());

        if (amount > card.getWithdrawLimit()) {
            return -2;
        }
        boolean success = account.withDraw(amount);
        if (success) {

            Date date = new Date();
            String type = "korttinosto";
            String from = card.getCardId();
            String to = getUser(Current.currentUser).getUserName(); ;


            account.addEvent(new Event(date, type, from, to, amount));


            return 1;
        }
        else {
            return -1;
        }

    }

    public static String saveUser(User user) {

        String fullJson = "";
        Gson gson = new Gson();
        fullJson = gson.toJson(user);
        System.out.println(fullJson);


        return fullJson;
    }

    public static String loadUser(String username, Context context) {

        FileInputStream fis = null;
        try {
            fis = context.openFileInput(username+"-data");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return null;
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

        try {

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }

        catch (IOException e) {
            return null;
        }

        String json = sb.toString();
        System.out.println("found userdata.");

        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        addUser(user);


        return user.getUserName();
    }



}
