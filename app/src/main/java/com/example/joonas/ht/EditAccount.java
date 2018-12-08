package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditAccount extends AppCompatActivity {

    RadioGroup accountType;
    RadioGroup canBeUsed;
    TextView curAccount;

    String type;
    boolean BcanBeUsed;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);

        //this is the intent from the EditAccounts activity
        intent = getIntent();
        String accountId = intent.getStringExtra("accountId");

        curAccount = findViewById(R.id.curAccount);
        curAccount.setText(accountId);

        accountType = findViewById(R.id.accountType);
        accountType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.savingAccount) {

                    type = "saving";
                }
                else if (checkedId == R.id.creditAccount) {

                    type = "credit";
                }

            }
        });

        canBeUsed = findViewById(R.id.canPay);
        canBeUsed.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes) {

                    BcanBeUsed = true;
                }
                else if (checkedId == R.id.no) {

                    BcanBeUsed = false;
                }
            }
        });
    }

    public void saveAccount(View v) {
        //todo: tallennetaan muutokset tiliin, ja palataan mainactivityyn.
        return;
    }
}
