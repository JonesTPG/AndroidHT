package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class CardPayment extends AppCompatActivity {

    Spinner spinneri;
    ArrayList<String> cardList;
    String selectedCard;

    EditText amount;
    TextView infoText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_payment);

        infoText = findViewById(R.id.infoText);
        amount = findViewById(R.id.paymentAmount);
        spinneri = findViewById(R.id.cardSpinner);
        cardList = Bank.getUser(Current.currentUser).getCardIds();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cardList);
        spinneri.setAdapter(adapter);
        spinneri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedCard = selectedItem;
                // do your stuff

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    public void payWithCard(View v) {
        String amountString = amount.getText().toString();
        int amountInt;
        try {
            amountInt = Integer.parseInt(amountString);

        }
        catch (NumberFormatException e) {
            infoText.setText("Tarkista lisättävä rahamäärä.");

            return;
        }

        if (selectedCard == null || selectedCard.length() == 0) {
            infoText.setText("Ei korttia valittuna.");
            return;
        }

        int success = Bank.cardPayment(selectedCard, amountInt);
        if (success == -2) {
            infoText.setText("Kortin maksuraja ylittyi.");
            return;
        }

        else if (success == -1) {
            infoText.setText("Tilillä ei ole tarpeeksi rahaa.");
            return;
        }

        else if (success == 1) {
            startActivity(new Intent(CardPayment.this, UserMain.class));
            return;
        }

        else {
            infoText.setText("Tapahtui virhe.");
            return;
        }



    }
}
