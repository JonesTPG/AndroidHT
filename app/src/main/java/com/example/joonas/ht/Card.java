package com.example.joonas.ht;


/*defines the Card object. Nothing special here.*/
public class Card {

    private String cardId;
    private String accountId;
    private int withdrawLimit;
    private int amountLimit;
    private String type;

    public Card(String cardiId, String accountId, int withdrawLimit, int amountLimit, String type) {
        this.cardId = cardiId;
        this.accountId = accountId;
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

    public void printInfo() {
        System.out.println(this.cardId);
        System.out.println(this.accountId);
        System.out.println(this.type);
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setWithdrawLimit(int withdrawLimit) {
        this.withdrawLimit = withdrawLimit;
    }

    public void setAmountLimit(int amountLimit) {
        this.amountLimit = amountLimit;
    }

    public void setType(String type) {
        this.type = type;
    }
}
