package com.example.myproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myproject.Adapter.todoAdapter;


public class profileFragment extends Fragment {


    public profileFragment() {
        // Required empty public constructor
    }
    Activity context;
    TextView txt_Comp_task;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();
        View view=  inflater.inflate(R.layout.fragment_profile, container, false);
        txt_Comp_task=view.findViewById(R.id.txt_Comp_task);



        return view;
    }
}