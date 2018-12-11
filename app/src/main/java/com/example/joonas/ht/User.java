package com.example.joonas.ht;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class User {

    String userName;
    String email;
    String address;
    String phone;



    ArrayList<Account> accounts;



    public User(String userName) {
        this.userName = userName;
        this.email = "ei määritetty";
        this.address = "ei määritetty";
        this.phone = "ei määritetty";
        this.accounts = new ArrayList();



    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void addAccount(Account newAccount) {
        accounts.add(newAccount);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<String> getAccountNames() {
        ArrayList<String> accountNames = new ArrayList<String>();

        for (int i=0; i<accounts.size(); i++) {
            accountNames.add(accounts.get(i).getId());
        }

        return accountNames;
    }

    public ArrayList<String> getCreditAccounts() {
        ArrayList<String> taulukko = new ArrayList<String>();
        for (int i=0; i<accounts.size(); i++) {
            if ( accounts.get(i).getType().equals("käyttötili") ) {
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

    public Card getCardById(String cardId) {
        for (int i=0; i<accounts.size(); i++) {
            Card card = accounts.get(i).getCard(cardId);
            if (card == null) {
                continue;
            }
            if (card.getCardId().equals(cardId)) {
                return card;
            }
        }
        return null;
    }




    public void printInfo() {
        System.out.println(this.userName);
        System.out.println(this.email);
        for (int i=0;i<accounts.size();i++) {
            System.out.println(accounts.get(i).id);
            System.out.println(accounts.get(i).balance);
            System.out.println(accounts.get(i).canBeUsed);
            System.out.println(accounts.get(i).type);
            for (int j=0; j<accounts.get(i).getCards().size(); j++) {
                Card kortti = accounts.get(i).getCards().get(j);
                System.out.println(j +":s kortti.");
                System.out.println(kortti.getCardId());
                System.out.println(kortti.getType());
            }

            for (int j=0; j<accounts.get(i).getEvents().size(); j++) {
                Event event = accounts.get(i).getEvents().get(j);
                System.out.println(j+":s tilitapahtuma.");
                System.out.println(event.getDate());
                System.out.println(event.getType());
                System.out.println(event.getFrom());
                System.out.println(event.getTo());
                System.out.println(event.getAmount());
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

    public int getEventsAmount() {
        int amount = 0;
        for (int i=0; i<accounts.size(); i++) {
            amount = amount + accounts.get(i).getEventsAmount();
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

    public ArrayList<String> getCardIds() {
        ArrayList<String> cards = new ArrayList<String>();
        for (int i=0; i<accounts.size(); i++) {
            for (int j=0; j<accounts.get(i).getCards().size(); j++) {
                cards.add(accounts.get(i).getCards().get(j).getCardId());
            }
        }
        return cards;
    }


}
