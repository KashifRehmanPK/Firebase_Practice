package com.example.firebase.Fifteen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.firebase.R;
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

public class uploadfile extends AppCompatActivity {

    ImageView imagebrowse,filelogo,cancelfile;
    Uri filepath2;
    EditText filetitle;
    Button imageupload;

    StorageReference storageReference;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadfile);


        storageReference= FirebaseStorage .getInstance().getReference();
        databaseReference= FirebaseDatabase .getInstance().getReference("mydocuments");

        filetitle=findViewById(R.id.filetitle);
        cancelfile=findViewById(R.id.cancelfile);
        filelogo=findViewById(R.id.filelogo);
        imagebrowse=findViewById(R.id.imagebrowse);




        filelogo.setVisibility(View.INVISIBLE);
        cancelfile.setVisibility(View.INVISIBLE);


        cancelfile.setVisibility(View.INVISIBLE);



        imageupload=(Button) findViewById(R.id.imageupload);




        cancelfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filelogo.setVisibility(View.INVISIBLE);
                cancelfile.setVisibility(View.INVISIBLE);
                imagebrowse.setVisibility(View.VISIBLE);


            }
        });


        imagebrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent= new Intent();
                                intent.setType("application/pdf");
                                intent.setAction( Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent,"select pdf path"),101);

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


        imageupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processupload(filepath2);
            }
        });


    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101 && resultCode==RESULT_OK){
            filepath2=data.getData();
            filelogo.setVisibility(View.VISIBLE);
            cancelfile.setVisibility(View.VISIBLE);
            imagebrowse.setVisibility(View.INVISIBLE);

        }


    }



    private void processupload(Uri filepath2) {

        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("File Uploading....!!!");
        pd.show();



        StorageReference reference = storageReference.child("PDF/"+System.currentTimeMillis()+".pdf");
        reference.putFile(filepath2)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {


                                pd.dismiss();
                                Toast.makeText(uploadfile.this, "File Uploaded", Toast.LENGTH_SHORT).show();

                                model_file_info obj=new model_file_info(filetitle.getText().toString(),uri.toString());
                                databaseReference.child(databaseReference.push().getKey()).setValue(obj);
                                filelogo.setVisibility(View.INVISIBLE);
                                cancelfile.setVisibility(View.INVISIBLE);
                                imagebrowse.setVisibility(View.VISIBLE);
                                filetitle.setText("");




                            }
                        });

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        float percent =  (100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                        pd.setMessage("Uploaded : "+(int)percent+" %");

                    }
                });

    }


}