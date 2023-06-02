package com.example.firebase.Nineteen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.firebase.R;

public class descfragment extends Fragment
{


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String contact, course, name, pimage;
    private String mParam1;
    private String mParam2;

    public descfragment()
    {

    }

    public descfragment(String contact, String course, String name, String pimage)
    {

        this.name = name;
        this.course = course;
        this.contact = contact;
        this.pimage = pimage;


    }


    public static descfragment newInstance(String param1, String param2)
    {
        descfragment fragment = new descfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_descfragment, container, false);

        TextView nameholder = view.findViewById(R.id.nameholder);
        TextView courseholder = view.findViewById(R.id.courseholder);
        TextView contactholder = view.findViewById(R.id.contact);
        ImageView imageholder = view.findViewById(R.id.imageholder);

        nameholder.setText(name);
        courseholder.setText(course);
        contactholder.setText(contact);
        Glide.with(getContext()).load(pimage).into(imageholder);

        return view;
    }


    public void onBackPressed()
    {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new recfragment()).addToBackStack(null).commit();

    }





}