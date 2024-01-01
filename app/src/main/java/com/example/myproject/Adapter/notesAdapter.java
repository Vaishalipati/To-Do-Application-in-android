package com.example.myproject.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.FullNote;
import com.example.myproject.NotesActivity;
import com.example.myproject.R;
import com.example.myproject.addnewnotes;
import com.example.myproject.model.notesmodel;
import com.example.myproject.utils.notesDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class notesAdapter extends RecyclerView.Adapter<notesAdapter.ViewHolder> {
    Context context;
    NotesActivity activity;
    ArrayList<notesmodel> arrnotes;
    notesDatabaseHelper notesdb;

    public notesAdapter(notesDatabaseHelper notesdb,Context context){
        this.context=context;
        this.notesdb=notesdb;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.notes_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txt_title.setText(arrnotes.get(position).title);
        holder.txt_note.setText(arrnotes.get(position).notes);
        holder.txt_date.setText(arrnotes.get(position).date);
    }


    @Override
    public int getItemCount() {
        return arrnotes.size();
    }
    public Context getContext(){
        return context;
    }
    public void setNotes(ArrayList<notesmodel> arrnotes){
        this.arrnotes=arrnotes;
        notifyDataSetChanged();
    }

    public void deleteTask(int position){
        notesmodel item=arrnotes.get(position);
        notesdb.deleteTask(item.getId());
        arrnotes.remove(position);
        notifyItemRemoved(position);
    }
    public void editTask(int position){
        notesmodel item=arrnotes.get(position);
        Bundle bundle=new Bundle();
        bundle.putInt("Id",item.getId());
        bundle.putString("task",item.getNotes());
        addnewnotes notes=new addnewnotes();
        notes.setArguments(bundle);
        notes.show(activity.getSupportFragmentManager(), notes.getTag());

    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_title,txt_date,txt_note;
        CardView notes_containerobj;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_date=itemView.findViewById(R.id.txt_date);
            txt_note=itemView.findViewById(R.id.txt_note);
            notes_containerobj=itemView.findViewById(R.id.notes_container);

            notes_containerobj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle=new Bundle();
                    Intent i=new Intent(context, FullNote.class);
                    startActivity(context,i,bundle);
                }
            });
        }
    }
}
