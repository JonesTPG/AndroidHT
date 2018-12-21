package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminMain extends AppCompatActivity {

    TextView infoText;
    Button logOutButton;
    Button addUserButton;

    ArrayList<User> userList;

    RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        infoText = findViewById(R.id.infoText);
        logOutButton = findViewById(R.id.logOut);
        logOutButton = findViewById(R.id.addUser);

        infoText.setText("Tervetuloa admin! Voit muokata käyttäjiä oheisesta listasta ja " +
                "halutessasi lisätä uuden painamalla nappia.");

        recyclerView = (RecyclerView) findViewById(R.id.userList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = Bank.getUserArray();

        UserAdapter adapteri = new UserAdapter(this, userList);
        recyclerView.setAdapter(adapteri);



    }

    public void logOut(View v) {
        //save the application state before logout
        int success = Bank.saveUsers(getApplicationContext());
        if (success == -1) {

            startActivity(new Intent(AdminMain.this, LoginActivity.class));

        }
        else {
            startActivity(new Intent(AdminMain.this, LoginActivity.class));

        }
    }

    public void addUser(View v) {
            startActivity(new Intent(AdminMain.this, AddUser.class));


    }
}

