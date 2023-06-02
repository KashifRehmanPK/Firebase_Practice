package com.example.firebase.Seventeen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firebase.R;

import java.util.List;

public class myadapter2 extends RecyclerView.Adapter<myadapter2.myviewholder> {


    List<String> files, status;

    public myadapter2(List<String> files, List<String> status) {
        this.files = files;
        this.status = status;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign2, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        String filename = files.get(position);
        if (filename.length() > 15)
            filename = filename.substring(0, 15) + "...";
        holder.filename.setText(filename);
        String filestatus = status.get(position);

        if (filestatus.equals("loading"))
            holder.pbar.setImageResource(R.drawable.ic_loading_circle_24);
        else
            holder.pbar.setImageResource(R.drawable.ic_check_box_24);


    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        ImageView pbar;
        TextView filename;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            filename = itemView.findViewById(R.id.filename);
            pbar = itemView.findViewById(R.id.pbar);


        }
    }


}
