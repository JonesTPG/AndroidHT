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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class Deposit extends AppCompatActivity {


    Spinner spinneri;
    ArrayList<String> accountList;
    String selectedAccount;

    EditText amount;
    TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        amount = findViewById(R.id.depositAmount);
        infoText = findViewById(R.id.infoText);

        spinneri = findViewById(R.id.accountSpinner);
        accountList = Bank.getUser(Current.currentUser).getAccountNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountList);
        spinneri.setAdapter(adapter);
        spinneri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedAccount = selectedItem;
                // do your stuff

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void depositMoney(View v) {
        String amountString = amount.getText().toString();
        int amountInt;
        try {
            amountInt = Integer.parseInt(amountString);

        }
        catch (NumberFormatException e) {
            infoText.setText("Tarkista lisättävä rahamäärä.");

            return;
        }
        User curUser = Bank.getUser(Current.currentUser);
        Account account = curUser.getAccount(selectedAccount);
        account.deposit(amountInt);

        //save the deposit to the account events

        Date date = new Date();
        String type = "deposit";
        String from = "bank";
        String to = selectedAccount;
        int amount = amountInt;

        account.addEvent(new Event(date, type, from, to, amount));

        startActivity(new Intent(Deposit.this, UserMain.class));
        return;

    }
}
