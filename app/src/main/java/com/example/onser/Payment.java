package com.example.onser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    CardView cod,bkash,card;

    Button home;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        cod= findViewById(R.id.cash_on_delivery_method);
        cod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(Payment.this);
                alert.setTitle("Payment Details");
                alert.setMessage("Cash On Delivery method selected\nYour Payment has been confirmed");
                alert.setIcon(R.drawable.confirmed_image);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(Payment.this,Login_preference.class));
                        finish();
                        Toast.makeText(Payment.this, "Payment Method confirmed", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog= alert.create();
                alertDialog.show();

            }
        });

        bkash= findViewById(R.id.bkash_method);
        bkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(Payment.this);
                alert.setTitle("Sorry!");
                alert.setMessage("This payment method is not available right now");
                //alert.setIcon(R.drawable.confirmed_image);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(Payment.this, "Payment confirmed", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog= alert.create();
                alertDialog.show();

            }
        });

        card= findViewById(R.id.card_method);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert= new AlertDialog.Builder(Payment.this);
                alert.setTitle("Sorry!");
                alert.setMessage("This payment method is not available right now");
                //alert.setIcon(R.drawable.confirmed_image);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(Payment.this, "Payment confirmed", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog= alert.create();
                alertDialog.show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert= new AlertDialog.Builder(Payment.this);
        alert.setTitle("Alert!");
        alert.setMessage("Please Select a payment method");
        alert.setPositiveButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alert.show();
    }
}