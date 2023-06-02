package com.example.firebase.TwentyFive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.firebase.R;
import com.google.firebase.database.DatabaseReference;

public class Spinner_Or_Dropdown extends AppCompatActivity
{

    EditText t1;
    Button btnadd;
    Spinner spinner;
    DatabaseReference dbref;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_or_dropdown);


        t1=(EditText)findViewById(R.id.t1);
        spinner=(Spinner)findViewById(R.id.spinnerdata) ;


    }
}