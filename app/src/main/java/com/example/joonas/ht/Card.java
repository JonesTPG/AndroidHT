package com.example.joonas.ht;

public class Card {

    private String cardId;
    private String accountId;
    private int withdrawLimit;
    private int amountLimit;
    private String type;

    public Card(String cardiId, String accountId, int withdrawLimit, int amountLimit, String type) {
        this.cardId = cardiId;
        this.withdrawLimit = withdrawLimit;
        this.amountLimit = amountLimit;
        this.type = type;
    }

    public String getCardId() {
        return cardId;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getWithdrawLimit() {
        return withdrawLimit;
    }

    public int getAmountLimit() {
        return amountLimit;
    }

    public String getType() {
        return type;
    }
}
