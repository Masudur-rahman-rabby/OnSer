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

public class worker_adapter extends RecyclerView.Adapter<worker_adapter.MyViewHolder> {
    Context context;
    ArrayList<workers> list;


    public worker_adapter(Context context, ArrayList<workers> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public worker_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.worker_details,parent,false);
        return new worker_adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull worker_adapter.MyViewHolder holder, int position) {

        workers w=list.get(position);
        holder.service_t.setText(w.getService());
        holder.num_t.setText(w.getPhone());
        holder.name_t.setText(w.getName());
        holder.w_option.setOnClickListener(view -> {

            PopupMenu popupMenu= new PopupMenu(context,holder.w_option);
            popupMenu.inflate(R.menu.w_menu);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){

                    case R.id.w_delete:
                        DAOworkers dao= new DAOworkers();
                        dao.remove(w.getKey()).addOnSuccessListener(success->{
                            AlertDialog.Builder alert= new AlertDialog.Builder(context);
                            alert.setTitle("Success!");
                            alert.setMessage("Worker Information Removed");
                            alert.setIcon(R.drawable.confirmed_image);
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    context.startActivity(new Intent(context,WorkerList.class));
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
    public static  class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name_t,num_t,service_t,w_option;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name_t= itemView.findViewById(R.id.admin_worker_name);
            num_t= itemView.findViewById(R.id.admin_worker_number);
            service_t= itemView.findViewById(R.id.admin_worker_service);
            w_option= itemView.findViewById(R.id.w_option);
        }
    }
}
