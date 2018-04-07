package com.example.ezburde.ft_hangouts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ContactsDB";
    private static final int    DB_VER = 1;

    public static final String  TABLE_NAME = "Contacts";
    public static final String  CONT_NAME = "Name";
    public static final String  CONT_PHONE = "Phone";
    public static final String  CONT_PHOTO = "Photo";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  query;

        query = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT NOT NULL, " +
                "%s TEXT NOT NULL, " +
                "%s TEXT", TABLE_NAME, CONT_NAME, CONT_PHONE, CONT_PHOTO);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String  query;

        query = String.format("DELETE TABLE IF EXIST %s", TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }
}
