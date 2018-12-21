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

public class TransferOther extends AppCompatActivity {

    Spinner userTo;
    Spinner from;


    String selectedFrom;
    String selectedUserTo;



    TextView infoText;
    EditText amount;

    ArrayList<String> accountList;
    ArrayList<String> userList;
    EditText accountTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_other);


        amount = findViewById(R.id.amount);
        infoText = findViewById(R.id.infoText);
        accountTo = findViewById(R.id.accountTo);

        from = findViewById(R.id.accountSpinnerFrom);
        userTo = findViewById(R.id.userSpinnerTo);


        userList = Bank.getUserNames();
        accountList = Bank.getUser(Current.currentUser).getAccountNames();


        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountList);
        from.setAdapter(adapterFrom);
        from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedFrom = selectedItem;
                // do your stuff

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter<String> adapterUserTo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, userList);
        userTo.setAdapter(adapterUserTo);
        userTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedUserTo = selectedItem;
                // do your stuff


            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void transferMoney(View v) {

        int iAmount;
        String selectedTo = accountTo.getText().toString();
        try {
            iAmount = Integer.parseInt(amount.getText().toString());

        }
        catch (NumberFormatException e) {
            infoText.setText("Tarkista lisättävä rahamäärä.");

            return;
        }

        if (selectedTo == null || selectedTo.equals("")) {
            infoText.setText("Tarkista tili, jolle raha siirretään.");
            return;
        }

        if (selectedFrom == null || selectedFrom.equals("")) {
            infoText.setText("Tarkista tili, jolta raha siirretään.");
            return;
        }

        if (selectedFrom.equals(selectedTo)) {
            infoText.setText("Tilit ovat samat");
            return;
        }

        int success = Bank.transferMoneyToOther(selectedTo, selectedUserTo, selectedFrom, iAmount);



        if (success == -3) {
            infoText.setText("Tiliä, jolta raha siirretään ei löytynyt.");
            return;
        }

        if (success == -4) {
            infoText.setText("Tili, jolta rahaa siirretään ei ole käytössä tai sen tyyppi on " +
                    "säästötili");
            return;
        }
        if ( success == -2) {
            infoText.setText("Käyttäjällä "+ selectedUserTo + " ei ole tiliä " + selectedTo);
            return;
        }
        if ( success == -1) {
            infoText.setText("Tilillä ei ole tarpeeksi rahaa.");
            return;
        }
        if (success == 1) {
            startActivity(new Intent(TransferOther.this, UserMain.class));
            return;
        }

        else {
            infoText.setText("Virhe on tapahtunut.");
            return;
        }



    }
}
