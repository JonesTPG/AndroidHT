package com.example.joonas.ht;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ProductViewHolder> {


    private Context mCtx;

    private ArrayList<Card> cardList;

    public CardAdapter(Context mCtx, ArrayList<Card> cardList) {
        this.mCtx = mCtx;
        this.cardList = cardList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout_card, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Card card = cardList.get(position);

        //binding the data with the viewholder views
        holder.cardTitle.setText("Kortin tyyppi:" + card.getType());
        holder.cardId.setText(card.getCardId());

        holder.amountlimit.setText("Maksuraja: "+ String.valueOf(Integer.toString(card.getAmountLimit())));
        holder.withdrawlimit.setText("Nostoraja: " + String.valueOf(Integer.toString(card.getWithdrawLimit())));



    }


    @Override
    public int getItemCount() {
        return cardList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {


        Button editButton;
        TextView cardTitle, cardId, amountlimit, withdrawlimit;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            cardTitle = itemView.findViewById(R.id.cardTitle);
            cardId = itemView.findViewById(R.id.cardId);
            editButton = itemView.findViewById(R.id.editButton);
            amountlimit = itemView.findViewById(R.id.amountlimit);
            withdrawlimit = itemView.findViewById(R.id.withdrawlimit);

        }
    }
}