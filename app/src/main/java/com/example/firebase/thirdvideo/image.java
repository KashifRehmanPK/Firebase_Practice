package com.example.firebase.thirdvideo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firebase.R;
import com.example.firebase.fourthvideo.textandimage_account_creation;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class image extends AppCompatActivity {


    ImageView img;
    Button browse, upload;
    Uri filepath;
    Bitmap bitmap;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);

        button = (Button) findViewById(R.id.button5);

        img = (ImageView) findViewById(R.id.imageView);
        upload = (Button) findViewById(R.id.upload);
        browse = (Button) findViewById(R.id.browse);

        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Giving permissions using dexter
                Dexter.withActivity(image.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                //INTENT
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "please select image"), 1);

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


        //UPLOAD IMAGE BUTTON
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String storageBucketUrl = "gs://fir-cbf03.appspot.com";

                ProgressDialog dialog = new ProgressDialog(image.this);
                dialog.setTitle("File Uploader");
                dialog.show();


                FirebaseStorage storage = FirebaseStorage.getInstance("gs://fir-cbf03.appspot.com");
                StorageReference uploader = storage.getReference().child("image1");
                uploader.putFile(filepath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                dialog.dismiss();
                                Toast.makeText(image.this, "file uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                float percent = (100 * snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                                dialog.setMessage("Uploaded :" + (int) percent + " %");

                            }
                        });
                //String storageRef = FirebaseStorage.getInstance(storageBucketUrl).reference;
            }

        });
    }


    public void next(View view) {

        Intent intent = new Intent(image.this, textandimage_account_creation.class);
        startActivity(intent);
    }


    //  Activity Reasult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            filepath = data.getData();
            try {

                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                img.setImageBitmap(bitmap);

            } catch (Exception ex) {

            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
