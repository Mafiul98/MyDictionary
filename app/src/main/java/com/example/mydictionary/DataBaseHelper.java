package com.example.mydictionary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseHelper extends SQLiteAssetHelper {
    public DataBaseHelper(Context context) {
        super(context, "dictionary.db", null, 1);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Dictionary",null);
        return cursor;
    }

    public Cursor searchWord(String keyword){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Dictionary where word like'%"+keyword+"%'",null);
        return cursor;

    }
}
