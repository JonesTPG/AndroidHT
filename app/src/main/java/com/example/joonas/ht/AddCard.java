package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class AddCard extends AppCompatActivity {

    Spinner spinneri;
    ArrayList<String> accountList;
    String selectedAccount = null;
    RadioGroup cardType;

    EditText cardId;
    EditText withdrawlimit;
    EditText amountlimit;
    String type = "nothing";

    TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);


        infoText = findViewById(R.id.infoText);
        spinneri = findViewById(R.id.accountSpinner);

        accountList = Bank.getUser(Current.currentUser).getCreditAccounts();

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

        cardId = findViewById(R.id.cardId);
        withdrawlimit = findViewById(R.id.withdrawlimit);
        amountlimit = findViewById(R.id.amountlimit);
        cardType = findViewById(R.id.cardType);

        cardType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.physical) {

                    type = "physicaĺ";
                }
                else if (checkedId == R.id.online) {

                    type = "online";
                }

            }
        });


    }

    public void validateCardandAdd(View v) {
        String sCardId = cardId.getText().toString();
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

        if ( type.equals("nothing") ) {
            infoText.setText("Määritä kortin toimivuus.");

            return;
        }

        if ( sCardId.length() == 0 || (selectedAccount == null) ) {
            infoText.setText("Tarkista liitettävä tili ja kortin numero.");

            return;
        }

        Account toBeAdded = Bank.getUser(Current.currentUser).getAccount(selectedAccount);
        toBeAdded.addCard(new Card(sCardId, selectedAccount, iWithdrawlimit, iAmountlimit, type));


        startActivity(new Intent(AddCard.this, UserMain.class));
        return;





    }
}
