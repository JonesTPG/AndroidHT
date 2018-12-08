package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;





public class EditCards extends AppCompatActivity {

    ArrayList<Card> cardList;

    RecyclerView recyclerView;

    CardAdapter adapteri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cards);


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cardList = Bank.getUser(Current.currentUser).getCards();

        adapteri = new CardAdapter(this, cardList);
        recyclerView.setAdapter(adapteri);
    }

    public void editCard(View v) {
        System.out.println(v.getId());
        startActivity(new Intent(EditCards.this, UserMain.class));
    }



}
