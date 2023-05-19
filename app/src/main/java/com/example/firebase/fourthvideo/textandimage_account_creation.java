package com.example.firebase.fourthvideo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firebase.R;
import com.example.firebase.fifthvideos.New_User_Authentication_Login_Logout_User_Managment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

public class textandimage_account_creation extends AppCompatActivity {

    EditText roll, name, course, contact;
    Uri filepath;
    ImageView img;
    Button browse, signup;
    Bitmap bitmap;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.textandimage_account_creation);

        img = (ImageView) findViewById(R.id.img);
        signup = (Button) findViewById(R.id.signup);
        browse = (Button) findViewById(R.id.browse);


        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(textandimage_account_creation.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "select image file"), 1);


                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog dialog = new ProgressDialog(textandimage_account_creation.this);
                dialog.setTitle("File Uploader");
                dialog.show();

                roll = (EditText) findViewById(R.id.t2);
                name = (EditText) findViewById(R.id.t3);
                course = (EditText) findViewById(R.id.t4);
                contact = (EditText) findViewById(R.id.t5);


                FirebaseDatabase db = FirebaseDatabase.getInstance();
                DatabaseReference root = db.getReference();
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference uploader = storage.getReference("Image"+new Random().nextInt(50));
                uploader.putFile(filepath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {

                                        dialog.dismiss();
                                        FirebaseStorage storage = FirebaseStorage.getInstance("gs://fir-cbf03.appspot.com");
                                        DatabaseReference root = db.getReference("student3");
                                        dataholder2 obj= new dataholder2(name.getText().toString(),contact.getText().toString(),course.getText().toString(),uri.toString());
                                        root.child(roll.getText().toString()).setValue(obj);
                                        name.setText("");
                                        course.setText("");
                                        contact.setText("");
                                        roll.setText("");
                                        img.setImageResource(R.drawable.ic_launcher_background);
                                        Toast.makeText(textandimage_account_creation.this, "Uploaded", Toast.LENGTH_SHORT).show();

                                    }
                                });

                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                                float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                                dialog.setMessage("Uploaded"+(int)percent+" %");

                            }
                        });


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK)
        {

            filepath=data.getData();
            try {

                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap= BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);

            }catch (Exception ex){

            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void next(View view) {

        button = (Button) findViewById(R.id.button2);
        Intent intent = new Intent(textandimage_account_creation.this, New_User_Authentication_Login_Logout_User_Managment.class);
        startActivity(intent);
    }
}