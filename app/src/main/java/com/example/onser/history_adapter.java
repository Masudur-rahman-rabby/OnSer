package com.example.onser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class history_adapter extends RecyclerView.Adapter<history_adapter.MyViewHolder> {

    Context context;
    ArrayList<BookingClass> list;

    public history_adapter(Context context, ArrayList<BookingClass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public history_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.history,parent,false);
        return new history_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull history_adapter.MyViewHolder holder, int position) {

        BookingClass bookingClass=list.get(position);
        holder.service.setText(bookingClass.getServices());
        holder.address.setText(bookingClass.getAddress());
        holder.date.setText(bookingClass.getDate());
        holder.time.setText(bookingClass.getTime());
        holder.cost.setText(bookingClass.getCost());
        holder.h_option.setOnClickListener(view -> {

            PopupMenu popupMenu= new PopupMenu(context,holder.h_option);
            popupMenu.inflate(R.menu.history_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){

                    case R.id.history_remove:
                        DAOhistory dao= new DAOhistory();
                        dao.remove(bookingClass.getKey()).addOnSuccessListener(success->{
                            AlertDialog.Builder alert= new AlertDialog.Builder(context);
                            alert.setTitle("Done!");
                            alert.setMessage("History Removed");
                            alert.setIcon(R.drawable.confirmed_image);
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    context.startActivity(new Intent(context,History.class));
                                    //Toast.makeText(Payment.this, "Payment confirmed", Toast.LENGTH_SHORT).show();
                                }
                            });
                            AlertDialog alertDialog= alert.create();
                            alertDialog.show();
                            Toast.makeText(context, "Removed Successfully", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            //context.startActivity(new Intent(context,Admin_panel.class));
                        }).addOnFailureListener(fail->{
                            Toast.makeText(context, "Can't Remove Item", Toast.LENGTH_SHORT).show();
                        });
                        break;
                }
                return false;
            });
            popupMenu.show();
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView service,address,date,time,cost,h_option;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            service= itemView.findViewById(R.id.history_service_name);
            address= itemView.findViewById(R.id.history_address);
            date= itemView.findViewById(R.id.history_date);
            time= itemView.findViewById(R.id.history_time);
            cost= itemView.findViewById(R.id.history_cost);
            h_option= itemView.findViewById(R.id.h_option);
        }
    }


}
