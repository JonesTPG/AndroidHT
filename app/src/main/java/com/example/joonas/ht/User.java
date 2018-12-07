package com.example.joonas.ht;

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
        }
    }

    public int getAccountsAmount() {
        return accounts.size();
    }


}
