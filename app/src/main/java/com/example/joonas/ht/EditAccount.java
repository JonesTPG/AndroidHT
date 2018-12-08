package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditAccount extends AppCompatActivity {

    RadioGroup accountType;
    RadioGroup canBeUsed;
    TextView curAccount;
    TextView infoText;

    String type = "";
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
        infoText = findViewById(R.id.infoText);
        curAccount = findViewById(R.id.curAccount);
        curAccount.setText(accountId);

        accountType = findViewById(R.id.accountType);
        accountType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.savingAccount) {

                    type = "säästötili";
                }
                else if (checkedId == R.id.creditAccount) {

                    type = "käyttötili";
                }

                else {
                    type = "";
                }

            }
        });

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
        //todo: tilin tyypin muuttaminen ei tällä hetkellä vielä muuta tilin classia oikein
        //todo: siispä se ei näy korttia lisätessä oikein
        
        User curUser = Bank.getUser(Current.currentUser);
        Account toBeModified = curUser.getAccount(accountId);
        if (!(type.length() == 0) && valueset ) {
            toBeModified.setType(type);
            toBeModified.setCanBeUsed(BcanBeUsed);
            startActivity(new Intent(EditAccount.this, UserMain.class));
            return;
        }

        else {
            infoText.setText("Valitse kumpaankin kenttään arvo.");
            return;
        }


    }
}
