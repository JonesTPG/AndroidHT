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


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ProductViewHolder> {


    private Context mCtx;



    private ArrayList<Event> eventList;

    public EventAdapter(Context mCtx, ArrayList<Event> eventList) {
        this.mCtx = mCtx;
        this.eventList = eventList;

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout_event, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        Event event = eventList.get(position);

        //binding the data with the viewholder views
        holder.eventDate.setText("Päivämäärä:" + event.getDate());
        holder.eventType.setText("Tyyppi:" + event.getType());
        holder.eventFrom.setText("Keneltä:" + event.getFrom());
        holder.eventTo.setText("Kenelle:" + event.getTo());
        holder.eventAmount.setText("Rahamäärä:" + Integer.toString(event.getAmount()));





    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {



        TextView eventDate, eventType, eventTo, eventFrom, eventAmount;


        public ProductViewHolder(View itemView) {
            super(itemView);

            eventDate = itemView.findViewById(R.id.eventDate);
            eventType = itemView.findViewById(R.id.eventType);
            eventFrom = itemView.findViewById(R.id.eventFrom);
            eventTo = itemView.findViewById(R.id.eventTo);
            eventAmount = itemView.findViewById(R.id.eventAmount);


        }
    }
}