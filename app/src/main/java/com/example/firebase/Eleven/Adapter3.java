package com.example.firebase.Eleven;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.R;
import com.example.firebase.ninth.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter3 extends FirebaseRecyclerAdapter<Model, Adapter3.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter3(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull Model model) {
        final int currentPosition = holder.getAdapterPosition();

        holder.contact.setText(model.getContact());
        holder.course.setText(model.getCourse());
        holder.name.setText(model.getName());
        Glide.with(holder.img.getContext()).load(model.getPimage()).into(holder.img);


        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogbox))
                        .setExpanded(true, 1000)
                        .create();


                View myview=dialogPlus.getHolderView();
                EditText purl=myview.findViewById(R.id.uimgurl);
                EditText name=myview.findViewById(R.id.uname);
                EditText course=myview.findViewById(R.id.ucourse);
                EditText contact=myview.findViewById(R.id.ucontact);
                Button submit=myview.findViewById(R.id.usubmit);

                purl.setText(model.getPimage());
                name.setText(model.getName());
                course.setText(model.getCourse());
                contact.setText(model.getContact());

                dialogPlus.show();


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        map.put("pimage",purl.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("course",course.getText().toString());
                        map.put("contact",contact.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("student3")
                                .child(getRef(currentPosition).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("student3")
                                .child(getRef(currentPosition).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    } // End of OnBindViewMethod




    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_firebase_recycler_view_design2, parent, false);
        myViewHolder viewHolder = new myViewHolder(listItem);
        return viewHolder;
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        ImageButton edit,delete;
        CircleImageView img;
        TextView contact,course,name;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img =(CircleImageView)itemView.findViewById(R.id.imageView);
            contact=(TextView) itemView.findViewById(R.id.textView);
            course=(TextView) itemView.findViewById(R.id.textView1);
            name=(TextView) itemView.findViewById(R.id.textView2);

            edit=(ImageButton) itemView.findViewById(R.id.editicon);
            delete=(ImageButton) itemView.findViewById(R.id.deleteicon);

        }
    }



}
