package com.example.firebase.Fifteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.firebase.R;
import com.example.firebase.Seventeen.MultipleFileUpload;
import com.example.firebase.Sixteen.model;
import com.example.firebase.Sixteen.myadapter;
import com.example.firebase.secondvideo.text;
import com.example.firebase.thirdvideo.image;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class UploadPDF extends AppCompatActivity {

    FloatingActionButton fb;
    RecyclerView recview;
    myadapter adapter;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fb = findViewById(R.id.floatingActionButton);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), uploadfile.class));
            }
        });

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("mydocuments"), model.class)
                        .build();

        adapter = new myadapter(options);
        recview.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening(); // Start listening for data changes
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening(); // Stop listening for data changes
    }

    public void btn999(View view) {

        button = (Button) findViewById(R.id.button12);
        Intent intent = new Intent(UploadPDF.this, MultipleFileUpload.class);
        startActivity(intent);


    }
}
