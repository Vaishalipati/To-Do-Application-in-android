package com.example.myproject.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myproject.model.notesmodel;
import com.example.myproject.model.todomodel;

import java.util.ArrayList;
import java.util.List;

public class notesDatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;


    private static final String DATABASE_NAME = "NOTES_DATABASE";
    private static final String TABLE_NAME = "NOTES_TABLE";
    private static final String COL_1_NAME = "ID";
    private static final String COL_2_NAME = "NOTES";
    private static final String COL_3_NAME = "TITLE";
    private static final String COL_4_NAME = "DATE";


    public notesDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , NOTES TEXT,TITLE TEXT,DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertTask(notesmodel model) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2_NAME, model.getNotes());
        values.put(COL_3_NAME,model.getTitle());
        values.put(COL_4_NAME,model.getDate());
        db.insert(TABLE_NAME, null, values);
    }

    public void updateTask(int id ,String notes){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2_NAME,notes);
        db.update(TABLE_NAME,values,"ID=?",new String[]{String.valueOf(id)});
    }
    public void deleteTask(int id){
        db=this.getWritableDatabase();
        db.delete(TABLE_NAME,"Id=?",new String[]{String.valueOf(id)});
    }

    @SuppressLint("Range")
    public ArrayList<notesmodel> getAllNotes(){
        db=this.getWritableDatabase();
        Cursor cursor=null;
        ArrayList<notesmodel> modelList = new ArrayList<>();

        db.beginTransaction();
        try {
            cursor=db.query(TABLE_NAME,null,null,null,null,null,null);
            if(cursor !=null){
                if(cursor.moveToFirst()){
                    do{
                        notesmodel notes=new notesmodel();
                        notes.setId(cursor.getInt(cursor.getColumnIndex(COL_1_NAME)));
                        notes.setNotes(cursor.getString(cursor.getColumnIndex(COL_2_NAME)));
                        notes.setTitle(cursor.getString(cursor.getColumnIndex(COL_3_NAME)));
                        notes.setDate(cursor.getString(cursor.getColumnIndex(COL_4_NAME)));
                        modelList.add(notes);
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
