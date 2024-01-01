package com.example.myproject;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class scoreFragment extends Fragment {


    public scoreFragment() {
        // Required empty public constructor
    }

    Activity context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getActivity();

        View view=inflater.inflate(R.layout.fragment_score, container, false);


        return view;
    }
}