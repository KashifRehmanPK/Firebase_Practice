package com.example.firebase.seventh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebase.Eight.google_login;
import com.example.firebase.R;
import com.example.firebase.fifthvideos.New_User_Authentication_Login_Logout_User_Managment;
import com.hbb20.CountryCodePicker;

public class OTP extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText t1;
    Button b1;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        t1=(EditText) findViewById(R.id.t1);
        ccp=(CountryCodePicker) findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        b1=(Button) findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(OTP.this, manageotp.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });



    }

    public void next(View view) {
        button = (Button) findViewById(R.id.button7);
        Intent intent = new Intent(OTP.this, google_login.class);
        startActivity(intent);


    }
}