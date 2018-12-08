package com.example.joonas.ht;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditProfile extends AppCompatActivity {


    EditText newAddress;
    EditText newEmail;
    EditText newPhone;

    TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        newAddress = findViewById(R.id.newAddress);
        newEmail = findViewById(R.id.newEmail);
        newPhone = findViewById(R.id.newPhone);

        infoText = findViewById(R.id.infoText);


    }

    public void saveProfile(View v) {
        String address = newAddress.getText().toString();
        String email = newEmail.getText().toString();
        String phone = newPhone.getText().toString();

        if ( !email.contains("@") ) {
            infoText.setText("Sähköposti ei ole oikeanlainen.");
            return;
        }

        User curUser  = Bank.getUser(Current.currentUser);
        curUser.setAddress(address);
        curUser.setEmail(email);
        curUser.setPhone(phone);

        System.out.println("Profile updated.");
        startActivity(new Intent(EditProfile.this, UserMain.class));
        return;
    }

}
