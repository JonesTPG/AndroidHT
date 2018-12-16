package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditUser extends AppCompatActivity {


    Intent intent;
    String userId;
    TextView user;

    Button editCards, editAccounts, deleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        intent = getIntent();
        userId = intent.getStringExtra("userName");


        editCards = findViewById(R.id.editCards);
        editAccounts = findViewById(R.id.editAccounts);
        deleteUser = findViewById(R.id.deleteUser);
        user = findViewById(R.id.user);

        user.setText("Muokkaat käyttäjää " + userId);


        User user = Bank.getUser(userId);

        Current.currentUser = userId;
        Current.isAdmin = true;

        System.out.println(Current.currentUser);





    }

    public void editAccounts(View v) {

        Intent intent= new Intent(this, EditAccounts.class);
        intent.putExtra("login","admin");
        startActivity(intent);


        return;
    }

    public void editCards(View v) {

        startActivity(new Intent(EditUser.this, EditCards.class));
        return;
    }

    public void editUserInfo(View v) {
        startActivity(new Intent(EditUser.this, EditProfile.class));
        return;
    }

    public void deleteUser(View v) {

        Bank.removeUser(userId, getApplicationContext());
        Bank.deleteDataFile(userId, getApplicationContext());

        startActivity(new Intent(EditUser.this, AdminMain.class));

        return;
    }


}
