package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    //define names of database
    public static String namedatabase ="database.db";
    public  String TableName="ToDoList";
    public  String Co1="ID";
    public  String Co2="Task";
//    public  String Co3="DoneTasks";

    public DataBase(@Nullable Context context) {
        super(context, namedatabase, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create Table in database
    db.execSQL("CREATE TABLE "+TableName+" ( "+Co2+" TEXT PRIMARY KEY )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //check if table exist
    db.execSQL("DROP TABLE IF EXISTS "+TableName);
    }

    //get all rows in table
    public ArrayList<String> GetAllTasks(String RowName){

        ArrayList<String> tasks = new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res =db.rawQuery("SELECT * FROM "+TableName,null);

        if (res.moveToFirst()) {
            while (!res.isAfterLast()) {
                String name = res.getString(res.getColumnIndex(RowName));

                tasks.add(name);
                res.moveToNext();
            }
        }
        return tasks;
    }

    //add data to table
    public boolean InsertData(String Task){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(Co2,Task);
        //contentValues.put(Co3,Checked);
        long result=db.insert(TableName,null,contentValues);

        if (result==-1)
            return false;
        else
            return true;
    }

    public boolean DeleteData(String Task){
        SQLiteDatabase db=this.getWritableDatabase();
        int result=db.delete(TableName,Co2+"=?",new String[]{Task});

        if (result==-1)
            return false;
        else
            return true;
    }


}
