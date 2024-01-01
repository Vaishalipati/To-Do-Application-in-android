package com.example.myproject.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myproject.model.todomodel;

import java.util.ArrayList;
import java.util.List;

public class databaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private static final String DATABASE_NAME = "TODO_DATABASE";
    private static final String TABLE_NAME = "TODO_TABLE";
    private static final String COL_1_NAME = "ID";
    private static final String COL_2_NAME = "TASK";
    private static final String COL_3_NAME = "STATUS";

    public databaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , TASK TEXT, STATUS INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public void insertTask(todomodel model) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2_NAME, model.getTask());
        values.put(COL_3_NAME, 0);
        db.insert(TABLE_NAME, null, values);
    }
    public void updateTask(int id ,String task){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2_NAME,task);
        db.update(TABLE_NAME,values,"ID=?",new String[]{String.valueOf(id)});
    }
    public void updateStatus(int id,int status){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_3_NAME,status);
        db.update(TABLE_NAME,values,"Id=?",new String[]{String.valueOf(id)});
    }
    public void deleteTask(int id){
        db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"Id=?",new String[]{String.valueOf(id)});
    }


    @SuppressLint("Range")
    public List<todomodel> getAllTask(){
        db=this.getWritableDatabase();
        Cursor cursor=null;
        List<todomodel> modelList = new ArrayList<>();

        db.beginTransaction();
        try {
            cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
            if(cursor !=null){
                if(cursor.moveToFirst()){
                    do{
                        todomodel task=new todomodel();
                        task.setId(cursor.getInt(cursor.getColumnIndex(COL_1_NAME)));
                        task.setTask(cursor.getString(cursor.getColumnIndex(COL_2_NAME)));
                        task.setStatus(cursor.getInt(cursor.getColumnIndex(COL_3_NAME)));
                        modelList.add(task);
                    }while (cursor.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            cursor.close();
        }
        return modelList;
    }
}
