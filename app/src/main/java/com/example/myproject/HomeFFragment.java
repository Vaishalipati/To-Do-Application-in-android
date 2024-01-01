package com.example.myproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class HomeFFragment extends Fragment {


    public HomeFFragment() {
        // Required empty public constructor
    }
    CardView cardtodoobj,cardnotes,cardcalender;
    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        context=getActivity();
        View view=  inflater.inflate(R.layout.fragment_home_f, container, false);

        cardtodoobj=view.findViewById(R.id.cardtodo);
        cardnotes=view.findViewById(R.id.cardnotes);
        cardcalender=view.findViewById(R.id.calendar);

        cardtodoobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,todoactivity.class);
                startActivity(i);
            }
        });
        cardnotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,NotesActivity.class);
                startActivity(i);
            }
        });
        cardcalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,CalenderActivity.class);
                startActivity(i);
            }
        });
        return view;

    }
}