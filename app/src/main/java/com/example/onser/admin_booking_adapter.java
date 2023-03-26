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

public class admin_booking_adapter extends RecyclerView.Adapter<admin_booking_adapter.MyViewHolder>{


    Context context;
    ArrayList<BookingClass> list;

    public admin_booking_adapter(Context context, ArrayList<BookingClass> list) {
        this.context = context;
        this.list = list;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.admin_booking,parent,false);
        return new admin_booking_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        BookingClass bookingClass=list.get(position);
        holder.service.setText(bookingClass.getServices());
        holder.address.setText(bookingClass.getAddress());
        holder.date.setText(bookingClass.getDate());
        holder.time.setText(bookingClass.getTime());
        holder.cost.setText(bookingClass.getCost());
        holder.ad_option.setOnClickListener(view -> {

            PopupMenu popupMenu= new PopupMenu(context,holder.ad_option);
            popupMenu.inflate(R.menu.booking_admin);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){

                    case R.id.work_done:
                        DAOadB dao= new DAOadB();
                        dao.remove(bookingClass.getKey()).addOnSuccessListener(success->{
                            AlertDialog.Builder alert= new AlertDialog.Builder(context);
                            alert.setTitle("Work Done");
                            alert.setMessage("Service delivered\nPayment confirmed");
                            alert.setIcon(R.drawable.confirmed_image);
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    context.startActivity(new Intent(context,Admin_Booking_list.class));
                                    //Toast.makeText(Payment.this, "Payment confirmed", Toast.LENGTH_SHORT).show();
                                }
                            });
                            AlertDialog alertDialog= alert.create();
                            alertDialog.show();
                            Toast.makeText(context, "Work Done", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            //context.startActivity(new Intent(context,Admin_panel.class));
                        }).addOnFailureListener(fail->{
                            Toast.makeText(context, "Can't change Item", Toast.LENGTH_SHORT).show();
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
    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView service,address,date,time,cost,ad_option;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            service= itemView.findViewById(R.id.ad_h_service_name);
            address= itemView.findViewById(R.id.ad_h_address);
            date= itemView.findViewById(R.id.ad_h_date);
            time= itemView.findViewById(R.id.ad_h_time);
            cost= itemView.findViewById(R.id.ad_h_cost);
            ad_option= itemView.findViewById(R.id.b_option);

        }
    }
}
