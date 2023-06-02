package com.example.firebase.Eighteen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firebase.Nineteen.RecyclerviewFragment;
import com.example.firebase.R;
import com.example.firebase.secondvideo.text;
import com.example.firebase.thirdvideo.image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class FirebaseNotification extends AppCompatActivity {


    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_notification);



        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribed";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe failed";
                        }
                    }
                });



    }

    public void btn333(View view) {

        button = (Button) findViewById(R.id.button13);
        Intent intent = new Intent(FirebaseNotification.this, RecyclerviewFragment.class);
        startActivity(intent);

    }
}