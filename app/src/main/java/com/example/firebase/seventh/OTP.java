package com.example.firebase.seventh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.firebase.Eight.google_login;
import com.example.firebase.R;
import com.hbb20.CountryCodePicker;

public class OTP extends AppCompatActivity {

    private CountryCodePicker countryCodePicker;
    private EditText phoneNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        countryCodePicker = findViewById(R.id.country_code_picker);
        phoneNumber = findViewById(R.id.signup_phone_number);
        Button next11 = findViewById(R.id.signup_next_button);
        Button google1 = findViewById(R.id.google11);


        countryCodePicker.registerCarrierNumberEditText(phoneNumber);

        next11.setOnClickListener(view -> {

            if (TextUtils.isEmpty(phoneNumber.getText().toString()))
            {
                Toast.makeText(OTP.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
            }
            else if (phoneNumber.getText().toString().replace(" ", "").length() != 10)
            {
                Toast.makeText(OTP.this, "Please enter a correct phone number", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(OTP.this, VerifyOTPActivity.class);
                intent.putExtra("phoneNo", countryCodePicker.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
                finish();
            }

        });


        google1.setOnClickListener(v -> {
            Intent intent = new Intent(OTP.this, google_login.class);
            startActivity(intent);
        });


    }








    public void google11(View view) {
        Intent intent = new Intent(OTP.this, google_login.class);
        startActivity(intent);
    }
}