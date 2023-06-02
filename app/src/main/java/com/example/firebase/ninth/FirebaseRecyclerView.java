package com.example.firebase.ninth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firebase.Fifteen.UploadPDF;
import com.example.firebase.R;
import com.example.firebase.Tenth.FirebaseRecyclerViewSearch;
import com.example.firebase.fifthvideos.New_User_Authentication_Login_Logout_User_Managment;
import com.example.firebase.seventh.OTP;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseRecyclerView extends AppCompatActivity {


    private RecyclerView recyclerView;
    Adapter adapter;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_recycler_view);


        recyclerView = findViewById(R.id.recyclerviewid22);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("student3"), Model.class)
                        .build();


        adapter = new Adapter(options);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    public void btn11(View view) {


        button = (Button) findViewById(R.id.button6);
        Intent intent = new Intent(FirebaseRecyclerView.this, FirebaseRecyclerViewSearch.class);
        startActivity(intent);
    }


    public void btn000(View view) {

        button = (Button) findViewById(R.id.button000);
        Intent intent = new Intent(FirebaseRecyclerView.this, UploadPDF.class);
        startActivity(intent);

    }
}