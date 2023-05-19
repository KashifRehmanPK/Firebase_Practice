 package com.example.firebase.sixthvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.firebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

 public class dashboard extends AppCompatActivity {



    TextView emailhome,uidhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        emailhome= (TextView) findViewById(R.id.email_home);
        uidhome= (TextView) findViewById(R.id.uidhome);

        emailhome.setText(getIntent().getStringExtra("email").toString());
        uidhome.setText("UID : "+getIntent().getStringExtra("uid").toString());

    }

    public void logout(View view) {

        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(dashboard.this,login.class));

    }
}