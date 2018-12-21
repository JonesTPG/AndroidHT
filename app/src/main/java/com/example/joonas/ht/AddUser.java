package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddUser extends AppCompatActivity {

    EditText username;
    EditText password;
    TextView infoText;

    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        username = findViewById(R.id.newUserName);
        password = findViewById(R.id.newUserPassword);
        add = findViewById(R.id.addNewUser);
        infoText = findViewById(R.id.infoText);


    }

    public void addUser(View v) {

        String newUserName = username.getText().toString();
        String newPassword = password.getText().toString();

        if ( newUserName.equals("") || newPassword.equals("") ){
            infoText.setText("tarkista käyttäjänimi ja salasana.");

        }

        else {
            User user = new User(newUserName);
            Bank.addUser(user);
            String credential = newUserName+":"+newPassword;
            Bank.addCredential(credential, getApplicationContext());
            int success = Bank.saveUsers(getApplicationContext());


            startActivity(new Intent(AddUser.this, AdminMain.class));


        }

    }
}
