package com.example.firebase.fifthvideos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.firebase.R;
import com.example.firebase.secondvideo.text;
import com.example.firebase.seventh.OTP;
import com.example.firebase.sixthvideo.login;
import com.example.firebase.thirdvideo.image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class New_User_Authentication_Login_Logout_User_Managment extends AppCompatActivity {

    EditText t1, t2;
    ProgressBar bar;
    Button button;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_authentication_login_logout_user_managment);


        t1 = (EditText) findViewById(R.id.email);
        t2 = (EditText) findViewById(R.id.pwd);
        bar = (ProgressBar) findViewById(R.id.progressBar);

    }

    public void signuphere(View view) {

        bar.setVisibility(View.VISIBLE);

        String email = t1.getText().toString();
        String password = t2.getText().toString();

        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            bar.setVisibility(View.INVISIBLE);
                            t1.setText("");
                            t2.setText("");
                            Toast.makeText(New_User_Authentication_Login_Logout_User_Managment.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        } else {

                            bar.setVisibility(View.INVISIBLE);
                            t1.setText("");
                            t2.setText("");
                            Toast.makeText(New_User_Authentication_Login_Logout_User_Managment.this, "Process error", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public void text(View view) {

        button = (Button) findViewById(R.id.button2);
        Intent intent = new Intent(New_User_Authentication_Login_Logout_User_Managment.this, login.class);
        startActivity(intent);


    }

    public void next(View view) {

        button = (Button) findViewById(R.id.button6);
        Intent intent = new Intent(New_User_Authentication_Login_Logout_User_Managment.this, OTP.class);
        startActivity(intent);

    }
}