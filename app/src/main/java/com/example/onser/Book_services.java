package com.example.onser;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Book_services extends AppCompatActivity {

    static final String TAG="Book_services";

    Spinner service_name;
    String[] services,time;
    Spinner timeofservices,cost;
    EditText address;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    Button booked;
    private TextView date;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    BookingClass bookingClass;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_services);

        address= (EditText) findViewById(R.id.booking_address);

        databaseReference= FirebaseDatabase.getInstance().getReference("BookingInformation");
        services= getResources().getStringArray(R.array.service_name);
        service_name= (Spinner) findViewById(R.id.booking_service);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spinner_view,R.id.spinner_text,services);
        service_name.setAdapter(adapter);
        time = getResources().getStringArray(R.array.time_of_booking);
        timeofservices=(Spinner) findViewById(R.id.booking_time);
        ArrayAdapter<String > adapter1= new ArrayAdapter<String>(this,R.layout.spinner_view,R.id.spinner_text,time);
        timeofservices.setAdapter(adapter1);
        cost =(Spinner) findViewById(R.id.service_cost);

        date= (TextView) findViewById(R.id.service_date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar= Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog= new DatePickerDialog(Book_services.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,year,month,day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis()-1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });


        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month= month+1;
                Log.d(TAG,"onDateSet: date: "+day+"/"+month+"/"+year);
                String displayDate= day+"/"+month+"/"+year;
                date.setText(displayDate);

            }
        };


        booked=(Button) findViewById(R.id.service_booking_button);
        booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name_of_service= service_name.getSelectedItem().toString().trim();
                String booking_address= address.getText().toString().trim();
                String booking_date= date.getText().toString().trim();
                String booking_time= timeofservices.getSelectedItem().toString().trim();
                String service_cost= cost.getSelectedItem().toString().trim();

                String key= databaseReference.push().getKey();

                if(name_of_service.isEmpty()){
                    Toast.makeText(Book_services.this,"Service Name Required",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(booking_address.isEmpty()){

                    address.setError("Address is Required");
                    address.requestFocus();
                    return;
                }
                if(booking_date.isEmpty()){
                    date.setError("Please Enter a Date");
                    date.requestFocus();
                    return;
                }
                if(booking_time.isEmpty()){

                    Toast.makeText(Book_services.this,"Service Time Required",Toast.LENGTH_SHORT).show();
                    return;

                }


                bookingClass= new BookingClass(name_of_service,booking_address,booking_date,booking_time,service_cost);
                FirebaseDatabase.getInstance().getReference("BookingInformation").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key).
                        setValue(bookingClass)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    FirebaseDatabase.getInstance().getReference("admin_booking_details").child(key).setValue(bookingClass);
                                    Toast.makeText(Book_services.this, "Boooking Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(Book_services.this,Booking_information_show.class);
                                    intent.putExtra("service_name",name_of_service);
                                    intent.putExtra("booking_address",booking_address);
                                    intent.putExtra("booking_date",booking_date);
                                    intent.putExtra("booking_time",booking_time);
                                    intent.putExtra("cost",service_cost);
                                    startActivity(intent);
                                    finish();

                                }else {
                                    Toast.makeText(Book_services.this,"Attempt unsuccessful",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}