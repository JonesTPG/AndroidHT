package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class AddAccount extends AppCompatActivity {


    EditText accountId;
    RadioGroup accountType;
    String type = "nothing";

    TextView creditLimitText;
    EditText creditLimit;

    TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        infoText = findViewById(R.id.infoText);

        creditLimitText = findViewById(R.id.creditLimitText);
        creditLimit = findViewById(R.id.creditLimit);

        accountId = findViewById(R.id.accountId);
        accountType = findViewById(R.id.accountType);
        accountType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.savingAccount) {

                    type = "saving";
                    creditLimitText.setVisibility(View.INVISIBLE);
                    creditLimit.setVisibility(View.INVISIBLE);
                }
                else if (checkedId == R.id.creditAccount) {

                    type = "credit";
                    creditLimitText.setVisibility(View.VISIBLE);
                    creditLimit.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    public void addAccount(View v) {


        if (accountId.getText().toString().length() == 0) {
            infoText.setText("Tarkista tilinumero.");
            return;
        }


        User curUser = Bank.getUser(Current.currentUser);
        if (type.equals("saving")) {
            curUser.addAccount(new Account(0, accountId.getText().toString(), 0, false,
                                    "säästötili"));

            startActivity(new Intent(AddAccount.this, UserMain.class));
            return;

        }
        else if (type.equals("credit")) {

            int iCreditLimit;
            try {
                iCreditLimit = Integer.parseInt(creditLimit.getText().toString());

            }
            catch (NumberFormatException e) {
                infoText.setText("Tarkista luottorajan arvo.");

                return;
            }

            curUser.addAccount(new Account(0, accountId.getText().toString(), iCreditLimit, true,
                                            "käyttötili"));

            startActivity(new Intent(AddAccount.this, UserMain.class));
            return;

        }
        else {
            infoText.setText("Valitse tilin tyyppi.");

            return;
        }
    }
}
