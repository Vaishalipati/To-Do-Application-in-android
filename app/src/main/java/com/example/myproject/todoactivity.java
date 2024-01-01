package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myproject.Adapter.todoAdapter;
import com.example.myproject.model.todomodel;
import com.example.myproject.utils.databaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class todoactivity extends AppCompatActivity implements onDialogCloseListner {

    private RecyclerView mRecyclerView;
    private FloatingActionButton fab;
    private TextView edtbottomobj;
    private databaseHelper mydb;
    private List<todomodel> mlist;
    private todoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoactivity);
        mRecyclerView=findViewById(R.id.recyclerview);
        fab=findViewById(R.id.fab);
        edtbottomobj=findViewById(R.id.edtbottom);
        mydb=new databaseHelper(todoactivity.this);
        mlist=new ArrayList<>();
        adapter=new todoAdapter(mydb,todoactivity.this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);

        mlist=mydb.getAllTask();
        Collections.reverse(mlist);
        adapter.setTstk(mlist);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
            }
        });
        edtbottomobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewTask.newInstance().show(getSupportFragmentManager(),AddNewTask.TAG);
            }
        });
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new RecyclerViewTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        mlist=mydb.getAllTask();
        Collections.reverse(mlist);
        adapter.setTstk(mlist);
        adapter.notifyDataSetChanged();
    }
}