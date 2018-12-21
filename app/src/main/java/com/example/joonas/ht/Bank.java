package com.example.joonas.ht;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;



/* class that makes all the important features happen. Keeps track of users, modifies them and
 * also is able to get and save data to the internal file storage. */


public class Bank {


    /*List of users*/
    static ArrayList<User> users = new ArrayList<User>();


    /*Singleton principel. (Even though this class pretty much doesn't need to be constructed
    * as the methods are all static.*/
    private static final Bank ourInstance = new Bank();
    public static Bank getInstance() {
        return ourInstance;
    }
    private Bank() {

    }



    public static void addUser(User user) {
        users.add(user);
    }

    public static ArrayList<User> getUserArray() {
        return users;
    }
    /*finds and returns a user object by username if one is found.*/
    public static User getUser(String username) {
        for (int i=0; i<users.size(); i++) {
            if ( users.get(i).userName.equals(username) ) {
                return users.get(i);
            }
        }
        return null;
    }

    /*moves money between acoounts of user*/
    public static int transferMoney(String fromId, String toId, int amount) {

        Account from = getUser(Current.currentUser).getAccount(fromId);
        Account to = getUser(Current.currentUser).getAccount(toId);

        if ( from.canBeUsed == false || from.getType().equals("säästötili") ) {
            return -2;
        }

        if ( from.getBalance()+from.getCreditLimit() < amount ) {
            System.out.println("From-tilillä ei ole tarpeeksi rahaa.");
            return -1;
        }
        else {

            from.withDraw(amount);
            to.deposit(amount);

            Date date = new Date();
            String type = "siirto itselle";
            from.addEvent(new Event(date, type, fromId, toId, amount));
            to.addEvent(new Event(date, type, fromId, toId, amount));

            System.out.println("Tilisiirto suoritettu.");
            return 1;
        }

    }
    /*moves money from one person's account to another user's account*/
    public static int transferMoneyToOther(String toId, String toUser, String fromId, int amount) {

        System.out.println("from:" +fromId + " to: " + toId + toUser + amount);

        Account from = getUser(Current.currentUser).getAccount(fromId);



        if (from == null) {
            return -3;
        }

        if (from.canBeUsed == false || from.getType().equals("säästötili")) {
            return -4;
        }
        Account to = getUser(toUser).getAccount(toId);
        if (to == null) {
            return -2;
        }

        boolean success = from.withDraw(amount);
        if (success == false) {
            return -1;
        }
        else {
            to.deposit(amount);
            //generate a new event and save to the accounts events
            Date date = new Date();
            String type = "siirto ulkopuolelle";
            from.addEvent(new Event(date, type, fromId, toId, amount));
            to.addEvent(new Event(date, type, fromId, toId, amount));
            return 1;


        }


    }


    /*makes a card payment*/
    public static int cardPayment(String cardId, int amount) {

        Card card = getUser(Current.currentUser).getCardById(cardId);
        Account account =  getUser(Current.currentUser).getAccount(card.getAccountId());

        if (amount > card.getAmountLimit()) {
            return -2;
        }
        boolean success = account.withDraw(amount);
        if (success) {

            Date date = new Date();
            String type = "korttimaksu";
            String from = card.getCardId();
            String to = "pankki";


            account.addEvent(new Event(date, type, from, to, amount));


            return 1;
        }
        else {
            return -1;
        }

    }

    /*"withdraws" money from the account the card is attached to*/
    public static int cardWithdraw(String cardId, int amount) {
        Card card = getUser(Current.currentUser).getCardById(cardId);
        Account account =  getUser(Current.currentUser).getAccount(card.getAccountId());

        if (amount > card.getWithdrawLimit()) {
            return -2;
        }
        boolean success = account.withDraw(amount);
        if (success) {

            Date date = new Date();
            String type = "korttinosto";
            String from = card.getCardId();
            String to = getUser(Current.currentUser).getUserName(); ;


            account.addEvent(new Event(date, type, from, to, amount));


            return 1;
        }
        else {
            return -1;
        }

    }

    /*makes a json object from the User class (Google json)*/
    public static String saveUser(User user) {

        String fullJson = "";
        Gson gson = new Gson();
        fullJson = gson.toJson(user);
        System.out.println(fullJson);


        return fullJson;
    }


    /*saves all the users at once. Used for example in logout operations.*/
    public static int saveUsers(Context context) {

        for (int i=0; i<users.size(); i++) {

            User user = users.get(i);
            String fullJson = "";
            Gson gson = new Gson();
            fullJson = gson.toJson(user);
            System.out.println(fullJson);

            if (fullJson != null) {
                String filename = user.getUserName()+"-data";

                FileOutputStream outputStream;

                try {
                    outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(fullJson.getBytes());
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return -1;
                }

            }


        }
        return 1;

    }

    /*loads the user data of a certain user. Notice the context parameter which is needed to
    make I/O -operations outside of a activity.
     */
    public static String loadUser(String username, Context context) {

        FileInputStream fis = null;
        try {
            fis = context.openFileInput(username+"-data");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return null;
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

        try {

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }

        catch (IOException e) {
            return null;
        }

        String json = sb.toString();
        System.out.println("found userdata.");

        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        addUser(user);


        return user.getUserName();
    }

    /*fetches the application credentials from internal storage file.
     */
    public static ArrayList<String> getCredentials(Context context) {



        FileInputStream fis = null;
        try {
            fis = context.openFileInput("credentials");
        } catch (FileNotFoundException e) {

            e.printStackTrace();
            return null;
        }
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;

        try {

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }

        catch (IOException e) {
            return null;
        }

        String json = sb.toString();
        System.out.println("found credentials.");


        Gson gson = new Gson();
        ArrayList<String> credentials = gson.fromJson(json, ArrayList.class);
        System.out.println(credentials);

        return credentials;

    }

    /*saves the credentials to a file.
     */
    public static int saveCredentials(ArrayList<String> credentials, Context context) {

        String fullJson = "";
        Gson gson = new Gson();
        fullJson = gson.toJson(credentials);
        System.out.println(fullJson);

        String filename = "credentials";

        FileOutputStream outputStream;

        try {
            outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(fullJson.getBytes());
            outputStream.close();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

    /*adds a single credential to the file. Makes use of the method above.*/
    public static int addCredential(String newCredential, Context context) {
        ArrayList<String> credentials = getCredentials(context);
        credentials.add(newCredential);
        System.out.println(credentials);
        saveCredentials(credentials, context);
        System.out.println("credentials saved");
        return 1;


    }


    /*removes a credential from the file. Needed when a user is removed.*/
    public static int deleteCredential(String username, Context context) {
        ArrayList<String> credentials = getCredentials(context);
        for (int i=0; i<credentials.size(); i++) {
            String credential = credentials.get(i);
            String[] pieces = credential.split(":");
            String user = pieces[0];
            if (user.equals(username)) {
                credentials.remove(i);
                saveCredentials(credentials, context);
                System.out.println("removed");
            }

        }

        return 1;
    }

    /*Removes the data file of a user. Needed when a user is removed.*/
    public static boolean deleteDataFile(String username, Context context) {
        boolean success = context.deleteFile(username+"-data");
        return success;
    }


    public static int removeUser(String userId, Context context) {
        for(int i=0; i<users.size(); i++) {

            if (userId.equals(users.get(i).getUserName())) {
                users.remove(i);
                deleteCredential(userId, context);
                System.out.println("removed");
                saveUsers(context);
            }

        }
        return 1;
    }

    /*returns the usernames of all the users, */
    public static ArrayList<String> getUserNames() {
        ArrayList<String> usernames = new ArrayList<String>();

        for (int i=0; i<users.size(); i++) {

            usernames.add(users.get(i).getUserName());

        }

        return usernames;

    }


    /*returns usernames based on the credential file. Credentials are stored in the file in this format:
    "username:password"
     */
    public static ArrayList<String> getUsers(Context context) {
        ArrayList<String> credentials = getCredentials(context);
        ArrayList<String> users = new ArrayList<String>();

        if (credentials == null) {
            return null;
        }

        for (int i=0; i<credentials.size(); i++) {
            users.add(credentials.get(i).split(":")[0]);
        }
        System.out.println(users);
        return users;
    }



    /*loads all the users to the bank object.*/
    public static void loadUsers(Context context) {


        ArrayList<String> usernames = getUsers(context);

        if (usernames == null) {
            return;
        }

        for (int i=0; i<usernames.size(); i++) {
            String username = usernames.get(i);


            FileInputStream fis = null;
            try {
                fis = context.openFileInput(username+"-data");
            } catch (FileNotFoundException e) {

                e.printStackTrace();
                continue;
            }
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;

            try {

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }
            }

            catch (IOException e) {
                continue;
            }

            String json = sb.toString();
            System.out.println("found userdata.");

            Gson gson = new Gson();
            User user = gson.fromJson(json, User.class);

            boolean alreadyIn = false;
            for (int j=0; j<users.size(); j++) {
                if (users.get(j).getUserName().equals(username)) {
                    alreadyIn = true;
                }

            }

            if (alreadyIn == false) {
                addUser(user);
            }



        }
        System.out.println(Bank.users);

        return;

    }


}
