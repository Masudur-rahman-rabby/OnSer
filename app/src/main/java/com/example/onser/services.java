package com.example.onser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class services extends RecyclerView.Adapter<services.MyViewHolder> {

    private  final ServiceListener serviceListener;

    Context context;

    String[] title,price;
    int[] image;

    public ServiceListener getServiceListener() {
        return serviceListener;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getPrice() {
        return price;
    }

    public void setPrice(String[] price) {
        this.price = price;
    }

    public int[] getImage() {
        return image;
    }

    public void setImage(int[] image) {
        this.image = image;
    }

    public services(Context context, String[] title, String[] price, int[] image, ServiceListener serviceListener) {
        this.context = context;
        this.title = title;
        this.price = price;
        this.image = image;
        this.serviceListener= serviceListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.service_layout_design,parent,false);
        return new MyViewHolder(view, serviceListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titleTextview.setText(title[position]);
        holder.priceTextview.setText(price[position]);
        holder.icon.setImageResource(image[position]);

    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleTextview,priceTextview;
        ImageView icon;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView, ServiceListener serviceListener) {
            super(itemView);
            titleTextview= itemView.findViewById(R.id.service_title);
            priceTextview= itemView.findViewById(R.id.service_price);
            icon= itemView.findViewById(R.id.imageview);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(serviceListener!= null){
                        int pos= getAdapterPosition();
                        if(pos!= RecyclerView.NO_POSITION){
                            serviceListener.onItemClicked(pos);
                        }
                    }
                }
            });
        }
    }
}
