package com.example.firebase.secondvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firebase.R;
import com.example.firebase.thirdvideo.image;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class text extends AppCompatActivity {


    Button button;
    EditText t2, t3, t4, t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);


    }

    public void onclick(View view) {

        t2 = (EditText) findViewById(R.id.t2);
        t3 = (EditText) findViewById(R.id.t3);
        t4 = (EditText) findViewById(R.id.t4);
        t5 = (EditText) findViewById(R.id.t5);

        String roll = t2.getText().toString().trim();
        String name = t3.getText().toString().trim();
        String course = t4.getText().toString().trim();
        String duration = t5.getText().toString().trim();

        dataholder obj = new dataholder(name, course, duration);
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference node = db.getReference("students");
        node.child(roll).setValue(obj);
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
        Toast.makeText(getApplicationContext(), "Inseted", Toast.LENGTH_SHORT).show();


    }


    public void next(View view) {
        button = (Button) findViewById(R.id.button2);
        Intent intent = new Intent(text.this, image.class);
        startActivity(intent);
    }

}