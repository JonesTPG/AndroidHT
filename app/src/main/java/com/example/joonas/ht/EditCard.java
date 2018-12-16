package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditCard extends AppCompatActivity {


    RadioGroup usability;
    TextView withdrawlimit;
    TextView amountlimit;
    TextView currentCard;
    TextView infoText;

    Intent intent;
    String cardId;

    String sUsability = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_card);

        intent = getIntent();
        cardId = intent.getStringExtra("cardId");

        currentCard = findViewById(R.id.curCard);
        currentCard.setText(cardId);

        infoText = findViewById(R.id.infoText);
        withdrawlimit = findViewById(R.id.withdrawlimit);
        amountlimit = findViewById(R.id.amountlimit);

        usability = findViewById(R.id.usability);
        usability.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.physical) {

                    sUsability = "physical";
                }
                else if (checkedId == R.id.online) {

                    sUsability = "online";
                }

                else {
                   sUsability = "";
                }

            }
        });
    }

    public void saveCard(View v) {

        User curUser = Bank.getUser(Current.currentUser);
        Card card = curUser.getCardById(cardId);
        card.printInfo();

        //todo: tallenna uudet tiedot kortti-olioon.

        if ( sUsability.equals("") ) {
            infoText.setText("Tarkista kortin toimivuus.");
            return;
        }

        int iWithdrawlimit;
        int iAmountlimit;

        try {
            iWithdrawlimit = Integer.parseInt(withdrawlimit.getText().toString());
            iAmountlimit = Integer.parseInt(amountlimit.getText().toString());
        }
        catch (NumberFormatException e) {
            infoText.setText("Tarkista nosto- ja maksuraja.");

            return;
        }



        card.setAmountLimit(iAmountlimit);
        card.setWithdrawLimit(iWithdrawlimit);
        card.setType(sUsability);

        if(Current.isAdmin == true) {
            startActivity(new Intent(EditCard.this, AdminMain.class));
            return;
        }

        else {
            startActivity(new Intent(EditCard.this, UserMain.class));

            return;
        }


    }
}
