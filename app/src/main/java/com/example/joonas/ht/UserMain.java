package com.example.joonas.ht;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextClock;
import android.widget.TextView;


public class UserMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    User curUser = Bank.getUser(Current.currentUser);


    TextView curUserName;
    TextView accountAmount;
    TextView cardAmount;
    TextView eventAmount;

    TextView address;
    TextView email;
    TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //TextView userNameView = findViewById(R.id.userNameText);
        //userNameView.setText(currentUserInfo.get("username").toString());

        curUserName = (TextView) findViewById(R.id.username);
        curUserName.setText(Current.currentUser);

        curUser.printInfo();

        accountAmount = findViewById(R.id.accountAmount);
        accountAmount.setText(Integer.toString(curUser.getAccountsAmount()));

        cardAmount = findViewById(R.id.cardAmount);
        cardAmount.setText(Integer.toString(curUser.getCardsAmount()));

        eventAmount = findViewById(R.id.eventAmount);
        eventAmount.setText(Integer.toString(curUser.getEventsAmount()));

        address = findViewById(R.id.address);
        address.setText(curUser.getAddress());

        email = findViewById(R.id.email);
        email.setText(curUser.getEmail());

        phone = findViewById(R.id.phone);
        phone.setText(curUser.getPhone());



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.add_item) {
            // Handle the camera action
            startActivity(new Intent(UserMain.this, AddAccount.class));

        }else if (id == R.id.add_card) {
            startActivity(new Intent(UserMain.this, AddCard.class));
        }

        else if (id == R.id.show_events) {

            startActivity(new Intent(UserMain.this, ShowEvents.class));

        } else if (id == R.id.manage_items) {

            startActivity(new Intent(UserMain.this, EditAccounts.class));


        }

        else if (id == R.id.manage_cards) {

            startActivity(new Intent(UserMain.this, EditCards.class));


        }

        else if (id == R.id.manage_profile) {

            startActivity(new Intent(UserMain.this, EditProfile.class));


        }

        else if (id == R.id.deposit) {
            startActivity(new Intent(UserMain.this, Deposit.class));
        }



        else if (id == R.id.move_own) {

        } else if (id == R.id.move_other) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
