package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;



public class EditAccounts extends AppCompatActivity {

    ArrayList<Account> accountList;

    RecyclerView recyclerView;

    Intent intent;
    String logintype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_accounts);

        intent = getIntent();
        logintype = intent.getStringExtra("login"); //check if the user role is admin or not





        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.accountList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        accountList = Bank.getUser(Current.currentUser).getAccounts();

        AccountAdapter adapteri = new AccountAdapter(this, accountList);
        recyclerView.setAdapter(adapteri);
    }






}
