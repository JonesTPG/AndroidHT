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

public class TransferOwn extends AppCompatActivity {

    Spinner from;
    Spinner to;

    String selectedFrom;
    String selectedTo;

    TextView infoText;
    EditText amount;

    ArrayList<String> accountList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_own);

        amount = findViewById(R.id.amount);
        infoText = findViewById(R.id.infoText);

        from = findViewById(R.id.accountSpinnerFrom);
        to = findViewById(R.id.accountSpinnerTo);

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




        ArrayAdapter<String> adapterTo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, accountList);
        to.setAdapter(adapterTo);
        to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                selectedTo = selectedItem;
                // do your stuff

            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void transferOwnMoney(View v) {

        int iAmount;
        try {
            iAmount = Integer.parseInt(amount.getText().toString());

        }
        catch (NumberFormatException e) {
            infoText.setText("Tarkista lisättävä rahamäärä.");

            return;
        }

        if (selectedFrom.equals(selectedTo)) {
            infoText.setText("Tilit ovat samat");
            return;
        }

        int success = Bank.transferMoney(selectedFrom, selectedTo, iAmount);
        if (success == 1) {
            startActivity(new Intent(TransferOwn.this, UserMain.class));
            return;
        }
        else {
            infoText.setText("Siirto ei onnistunut.");
            return;
        }



    }
}
