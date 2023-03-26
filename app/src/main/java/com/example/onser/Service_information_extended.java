package com.example.onser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Service_information_extended extends AppCompatActivity {

    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_information_extended);

        int image= getIntent().getIntExtra("image",0);
        String name = getIntent().getStringExtra("name");
        String cost = getIntent().getStringExtra("cost");

        ImageView image_textview = (ImageView) findViewById(R.id.ser_img);
        TextView name_t=(TextView) findViewById(R.id.ser_name);
        TextView cost_t =(TextView) findViewById(R.id.ser_cost);

        image_textview.setImageResource(image);
        name_t.setText(name);
        cost_t.setText(cost);

        button= findViewById(R.id.proceed_to_book);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Service_information_extended.this,Book_services.class));
                finish();
            }
        });
        TextView back= (TextView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Service_information_extended.this,Services_info.class));
                finish();
            }
        });

    }
}