package com.example.joonas.ht;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.ProductViewHolder> {


    private Context mCtx;



    private ArrayList<Account> accountList;

    public AccountAdapter(Context mCtx, ArrayList<Account> accountList) {
        this.mCtx = mCtx;
        this.accountList = accountList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Account account = accountList.get(position);

        //binding the data with the viewholder views
        holder.accountTitle.setText("Tilin tyyppi:" + account.getType());
        holder.accountId.setText(account.getId());

        holder.balance.setText("Saldo: "+ String.valueOf(Integer.toString(account.balance)));



    }


    @Override
    public int getItemCount() {
        return accountList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {


        Button editButton;
        TextView accountTitle, accountId, balance;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            accountTitle = itemView.findViewById(R.id.accountTitle);
            accountId = itemView.findViewById(R.id.accountId);
            editButton = itemView.findViewById(R.id.editButton);
            balance = itemView.findViewById(R.id.balance);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent= new Intent(mCtx, EditAccount.class);
                    intent.putExtra("accountId",accountId.getText().toString());
                    mCtx.startActivity(intent);


                }
            });

        }
    }
}