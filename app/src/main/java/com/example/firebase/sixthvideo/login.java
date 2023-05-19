package com.example.firebase.sixthvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    TextView t1,t2;
    ProgressBar bar;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t1=(TextView)findViewById(R.id.email_login);
        t2=(TextView)findViewById(R.id.pwd_login);
        bar=(ProgressBar)findViewById(R.id.progressBar_login);
        mAuth = FirebaseAuth.getInstance();
    }

    public void signuphere_login(View view)
    {
        bar.setVisibility(View.VISIBLE);
        String email=t1.getText().toString();
        String password=t2.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            bar.setVisibility(View.INVISIBLE);
                            Intent intent=new Intent(login.this,dashboard.class);
                            intent.putExtra("email",mAuth.getCurrentUser().getEmail());
                            intent.putExtra("uid",mAuth.getCurrentUser().getUid());
                            startActivity(intent);
                        } else
                        {
                            bar.setVisibility(View.INVISIBLE);
                            t1.setText("");
                            t2.setText("");
                            Toast.makeText(getApplicationContext(),"Invalid email/password",Toast.LENGTH_LONG).show();
                        }

                        // ...
                    }
                });



    }
}