package com.example.project_se_semester_4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Register.db";

    public DBHelper(Context context) {
        super(context, "Register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key, first_name TEXT, last_name TEXT, email TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String username, String first_name, String last_name, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long results = MyDB.insert("users", null, contentValues);
        if(results == -1) return false;
        else return true;
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ?", new String[] {username});
        if(cursor.getCount() > 0){
            return true;}
        else{
            return false;}
    }

    public Boolean checkInsert(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if(cursor.getCount()>0){
            return true;}
        else {
            return false;}
    }
}

