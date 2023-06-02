package com.example.firebase.seventh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firebase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyOTPActivity extends AppCompatActivity {


    private EditText pinFromUser;
    private Button submit;
    private FirebaseAuth mAuth;
    private String number,codeBySystem;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp_activity);
        pinFromUser = findViewById(R.id.pin_view);
        submit= findViewById(R.id.submit);

        mAuth = FirebaseAuth.getInstance();
        number = getIntent().getStringExtra("phoneNo");


        sendVerificationCode(number);


/*
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(pinFromUser.getText().toString())) {
                    Toast.makeText(VerifyOTPActivity.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                } else if (pinFromUser.getText().toString().replace(" ", "").length() != 6) {
                    Toast.makeText(VerifyOTPActivity.this, "enter right OTP", Toast.LENGTH_SHORT).show();
                } else {

                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id, pinFromUser.getText().toString().replace(" ", ""));
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });*/


    }

    private void sendVerificationCode(String number) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

                                String code = credential.getSmsCode();
                                //signInWithPhoneAuthCredential(credential);
                                if(code!=null){
                                    pinFromUser.setText(code);
                                    verifyCode(code);
                                }
                            }


                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {


                                Toast.makeText(VerifyOTPActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                System.out.println(e.getMessage());
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {

                                super.onCodeSent(verificationId,token);
                                codeBySystem=verificationId;
                                //VerifyOTPActivity.this.id = verificationId;

                            }
                        }
)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(credential);
    }


    public void callNextScreenFromOTP(View view) {
        String code = pinFromUser.getText().toString();
        if(!code.isEmpty()){
            verifyCode(code);
        }

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            startActivity(new Intent(VerifyOTPActivity.this,Welcome.class));
                            finish();
                            FirebaseUser user = task.getResult().getUser();
                            Toast.makeText(VerifyOTPActivity.this, "Verification Completed", Toast.LENGTH_SHORT).show();

                        } else {

                            Toast.makeText(VerifyOTPActivity.this, "Verification Failed", Toast.LENGTH_SHORT).show();


                        }
                    }
                });

    }


}