package com.example.firebase.Nineteen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<model11, myadapter.myviewholder> {


    public myadapter(@NonNull FirebaseRecyclerOptions<model11> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model11 model) {

        holder.nametext.setText(model.getName());
        holder.coursetext.setText(model.getCourse());
        holder.contact.setText(model.getContact());
        Glide.with(holder.img1.getContext()).load(model.getPimage()).into(holder.img1);

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity=(AppCompatActivity) view. getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new descfragment(model.getContact(),model.getCourse(),model.getName(),model.getPimage())).addToBackStack(null).commit();

            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign4, parent, false);
        return new myviewholder(view);

    }

    public class myviewholder extends RecyclerView.ViewHolder {

        ImageView img1;
        TextView nametext, coursetext, contact;


        public myviewholder(@NonNull View itemView) {
            super(itemView);


            img1 = itemView.findViewById(R.id.img1);
            nametext = itemView.findViewById(R.id.nametext);
            coursetext = itemView.findViewById(R.id.coursetext);
            contact = itemView.findViewById(R.id.contact);


        }
    }

}
