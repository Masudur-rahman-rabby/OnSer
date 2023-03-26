package com.example.onser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class Services_info extends AppCompatActivity implements ServiceListener {

    int[] images={
            R.drawable.electrician,R.drawable.plumberr,R.drawable.carpent,
            R.drawable.masons,R.drawable.pastecontrol,R.drawable.securityguard,
            R.drawable.maid,R.drawable.wifiprovider,R.drawable.labor

    };

    services ser;
    String[] title,price;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_info);

        recyclerView=(RecyclerView) findViewById(R.id.service_info_view);
        title=getResources().getStringArray(R.array.service_name);
        price=getResources().getStringArray(R.array.service_price);

        ser =new services(this,title,price,images, this);

        recyclerView.setAdapter(ser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemClicked(int position) {

        Intent intent= new Intent(Services_info.this,Service_information_extended.class);
        intent.putExtra("image",ser.image[position]);
        intent.putExtra("name",ser.title[position]);
        intent.putExtra("cost",ser.price[position]);

        startActivity(intent);
        finish();

    }
}