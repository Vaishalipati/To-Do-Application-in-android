package com.example.myproject.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.AddNewTask;
import com.example.myproject.R;
import com.example.myproject.model.todomodel;
import com.example.myproject.todoactivity;
import com.example.myproject.utils.databaseHelper;

import java.util.List;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.MyViewHolder> {
    public List<todomodel> mlist;
    private todoactivity activity;
    private databaseHelper mydb;
    

    public todoAdapter(databaseHelper mydb,todoactivity activity){
        this.activity=activity;
        this.mydb = mydb;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.tasklayout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final todomodel item= mlist.get(position);
        holder.addcheck.setText(item.getTask());
        holder.addcheck.setChecked(toBoolean(item.getStatus()));
        int p=position;

        holder.addcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    deleteTask(p);
                    mydb.updateStatus(item.getId(),1);
                    Toast.makeText(activity, "Task Completed", Toast.LENGTH_SHORT).show();
                }
                else {
                    mydb.updateStatus(item.getId(),0);
                }
            }
        });
    }
    public boolean toBoolean(int num){
        return num!=0;
    }
    public Context getContext(){
        return activity;
    }
    public void setTstk(List<todomodel> mlist){
        this.mlist=mlist;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }
    public void deleteTask(int position){
        todomodel item=mlist.get(position);
        mydb.deleteTask(item.getId());
        mlist.remove(position);
        notifyItemRemoved(position);
    }

    public void editTask(int position){
        todomodel item=mlist.get(position);
        Bundle bundle=new Bundle();
        bundle.putInt("Id",item.getId());
        bundle.putString("task",item.getTask());
        AddNewTask task=new AddNewTask();
        task.setArguments(bundle);
        task.show(activity.getSupportFragmentManager(), task.getTag());
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox addcheck;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            addcheck=itemView.findViewById(R.id.addcheck);
        }
    }
}
