package com.example.onser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userlist_adapter extends RecyclerView.Adapter<userlist_adapter.MyViewHolder> {

    Context context;

    ArrayList<Getuser> list;

    public userlist_adapter(Context context, ArrayList<Getuser> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.userlist,parent,false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Getuser getuser=list.get(position);
        holder.user_name.setText(getuser.getName());
        holder.user_email.setText(getuser.getEmail());
        holder.user_phone_number.setText(getuser.getPhonenumber());
        holder.user_address.setText(getuser.getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView user_name, user_email, user_phone_number, user_address;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_name= itemView.findViewById(R.id.userlist_username);
            user_email= itemView.findViewById(R.id.userlist_email);
            user_phone_number= itemView.findViewById(R.id.userlist_number);
            user_address= itemView.findViewById(R.id.userlist_address);
        }
    }
}
