package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditAccount extends AppCompatActivity {


    RadioGroup canBeUsed;
    TextView curAccount;
    TextView infoText;

    TextView creditLimitText;
    EditText creditLimit;


    boolean BcanBeUsed;
    boolean valueset = false;


    Intent intent;
    String accountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        //this is the intent from the EditAccounts activity
        intent = getIntent();
        accountId = intent.getStringExtra("accountId");
        User curUser = Bank.getUser(Current.currentUser);
        Account toBeModified = curUser.getAccount(accountId);
        infoText = findViewById(R.id.infoText);
        curAccount = findViewById(R.id.curAccount);
        curAccount.setText(accountId);

        creditLimitText = findViewById(R.id.creditLimitText);
        creditLimit = findViewById(R.id.creditLimit);

        if (toBeModified.getType().equals("käyttötili")) {
            creditLimitText.setVisibility(View.VISIBLE);
            creditLimit.setVisibility(View.VISIBLE);

        }


        canBeUsed = findViewById(R.id.canPay);
        canBeUsed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes) {

                    BcanBeUsed = true;
                    valueset = true;
                }
                else if (checkedId == R.id.no) {

                    BcanBeUsed = false;
                    valueset = true;
                }
                else {
                    valueset = false;

                }
            }
        });
    }

    public void saveAccount(View v) {


        User curUser = Bank.getUser(Current.currentUser);
        Account toBeModified = curUser.getAccount(accountId);


        if ( valueset && toBeModified.getType().equals("säästötili")) {
            toBeModified.setCanBeUsed(BcanBeUsed);
            startActivity(new Intent(EditAccount.this, UserMain.class));
            return;
        }

        else if ( valueset && toBeModified.getType().equals("käyttötili")) {
            int iCreditLimit;
            try {
                iCreditLimit = Integer.parseInt(creditLimit.getText().toString());

            }
            catch (NumberFormatException e) {
                infoText.setText("Tarkista luottorajan arvo.");
                return;
            }

            toBeModified.setCanBeUsed(BcanBeUsed);
            toBeModified.setCreditLimit(iCreditLimit);
            startActivity(new Intent(EditAccount.this, UserMain.class));
            return;
        }

        else {
            infoText.setText("Valitse kumpaankin kenttään arvo.");
            return;
        }


    }
}
