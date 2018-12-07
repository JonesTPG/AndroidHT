package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AddAccount extends AppCompatActivity {


    EditText accountId;
    RadioGroup accountType;
    String type = "nothing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        accountId = findViewById(R.id.accountId);
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
    }

    public void addAccount(View v) {
        User curUser = Bank.getUser(Current.currentUser);
        if (type.equals("saving")) {
            curUser.addAccount(new SavingAccount(0, accountId.getText().toString()));
            System.out.println("success");
            startActivity(new Intent(AddAccount.this, UserMain.class));
            return;

        }
        else if (type.equals("credit")) {
            curUser.addAccount(new CreditAccount(0, accountId.getText().toString(), 0));
            System.out.println("success2");
            startActivity(new Intent(AddAccount.this, UserMain.class));
            return;

        }
        else {
            System.out.println("Tilin tyyppiä ei ole valittu.");
            return;
        }
    }
}
