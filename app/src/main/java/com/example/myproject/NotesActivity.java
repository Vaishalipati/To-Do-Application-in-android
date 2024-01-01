package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myproject.Adapter.notesAdapter;
import com.example.myproject.Adapter.todoAdapter;
import com.example.myproject.model.notesmodel;
import com.example.myproject.utils.databaseHelper;
import com.example.myproject.utils.notesDatabaseHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class NotesActivity extends AppCompatActivity implements onDialogCloseListner {

    private RecyclerView recyclerView;
    private FloatingActionButton fab_note;
    private notesDatabaseHelper notesdb;
    private ArrayList<notesmodel> notesmodel;
    private notesAdapter notesAdapter;
    TextView txt_bottomobj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

       recyclerView=findViewById(R.id.recyclerview);
       fab_note=findViewById(R.id.fab_note);
       notesdb=new notesDatabaseHelper(NotesActivity.this);
       notesmodel=new ArrayList<>();
       notesAdapter=new notesAdapter(notesdb,NotesActivity.this);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(notesAdapter);

        txt_bottomobj=findViewById(R.id.txt_bottom);


        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new NotesItemTouchHelper(notesAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        fab_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnewnotes.newInstance().show(getSupportFragmentManager(),addnewnotes.TAG);
            }
        });


        txt_bottomobj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addnewnotes.newInstance().show(getSupportFragmentManager(),addnewnotes.TAG);
            }
        });


        notesmodel=notesdb.getAllNotes();
        Collections.reverse(notesmodel);
        notesAdapter.setNotes(notesmodel);
    }

    @Override
    public void onDialogClose(DialogInterface dialogInterface) {
        notesmodel=notesdb.getAllNotes();
        Collections.reverse(notesmodel);
        notesAdapter.setNotes(notesmodel);
        notesAdapter.notifyDataSetChanged();
    }
}