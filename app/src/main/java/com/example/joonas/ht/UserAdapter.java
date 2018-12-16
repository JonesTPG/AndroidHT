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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ProductViewHolder> {




        private Context mCtx;

        private ArrayList<User> userList;

        public UserAdapter(Context mCtx, ArrayList<User> userList) {
            this.mCtx = mCtx;
            this.userList = userList;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflating and returning our view holder
            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.layout_users, null);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {
            //getting the product of the specified position
            User user = userList.get(position);

            //binding the data with the viewholder views
            holder.userName.setText(user.getUserName());





        }


        @Override
        public int getItemCount() {
            return userList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder {


            Button editButton;
            TextView userName;


            public ProductViewHolder(View itemView) {
                super(itemView);

                userName = itemView.findViewById(R.id.userName);

                editButton = itemView.findViewById(R.id.editButton);


                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent= new Intent(mCtx, EditUser.class);
                        intent.putExtra("userName",userName.getText().toString());
                        mCtx.startActivity(intent);


                    }
                });

            }
        }
    }

