package com.example.firebase.Nineteen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.firebase.R;

public class RecyclerviewFragment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_fragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment()).commit();


    }
}