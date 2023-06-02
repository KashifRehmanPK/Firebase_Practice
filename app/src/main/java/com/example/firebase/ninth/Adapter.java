package com.example.firebase.ninth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.firebase.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter  extends FirebaseRecyclerAdapter<Model,Adapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public Adapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Model model) {

        holder.contact.setText(model.getContact());
        holder.course.setText(model.getCourse());
        holder.name.setText(model.getName());
        Glide.with(holder.img.getContext()).load(model.getPimage()).into(holder.img);

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_firebase_recycler_view_design, parent, false);
        Adapter.myViewHolder viewHolder = new Adapter.myViewHolder(listItem);
        return viewHolder;
    }

    class myViewHolder extends RecyclerView.ViewHolder{


        CircleImageView img;
        TextView contact,course,name;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            img =(CircleImageView)itemView.findViewById(R.id.imageView);
            contact=(TextView) itemView.findViewById(R.id.textView);
            course=(TextView) itemView.findViewById(R.id.textView1);
            name=(TextView) itemView.findViewById(R.id.textView2);

        }
    }



}
