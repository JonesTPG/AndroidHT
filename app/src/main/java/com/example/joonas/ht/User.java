package com.example.joonas.ht;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class User {

    String userName;
    String email;



    ArrayList<Account> accounts;
    HashMap<String, String> settings;


    public User(String userName) {
        this.userName = userName;
        this.email = "example@example.fi";
        this.accounts = new ArrayList();
        this.settings = new HashMap<String, String>();


    }

    public void addAccount(Account newAccount) {
        accounts.add(newAccount);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<String> getCreditAccounts() {
        ArrayList<String> taulukko = new ArrayList<String>();
        for (int i=0; i<accounts.size(); i++) {
            if ( accounts.get(i) instanceof CreditAccount ) {
                taulukko.add(accounts.get(i).getId());
            }
        }
        return taulukko;
    }


    public Account getAccount(String ID) {
        for (int i=0; i<accounts.size(); i++) {
            if ( accounts.get(i).id.equals(ID) ) {
                return accounts.get(i);
            }
        }

        return null;
    }

    public HashMap<String, Object> getInfo() {
        HashMap<String, Object> info = new HashMap<String, Object>();
        info.put("username", this.userName);
        info.put("email", this.email);
        info.put("accounts", this.accounts);
        info.put("settings", this.settings);

        return info;
    }


    public void printInfo() {
        System.out.println(this.userName);
        System.out.println(this.email);
        for (int i=0;i<accounts.size();i++) {
            System.out.println(accounts.get(i).id);
            System.out.println(accounts.get(i).balance);
            for (int j=0; j<accounts.get(i).getCards().size(); j++) {
                Card kortti = accounts.get(i).getCards().get(j);
                System.out.println(j +":s kortti.");
                System.out.println(kortti.getCardId());
                System.out.println(kortti.getType());
            }
        }
    }

    public int getAccountsAmount() {
        return accounts.size();
    }

    public int getCardsAmount() {
        int amount = 0;
        for (int i=0; i<accounts.size(); i++) {
            amount = amount + accounts.get(i).getCards().size();
        }
        return amount;
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i=0; i<accounts.size(); i++) {
            for (int j=0; j<accounts.get(i).getCards().size(); j++) {
                cards.add(accounts.get(i).getCards().get(j));
            }
        }
        return cards;
    }


}
