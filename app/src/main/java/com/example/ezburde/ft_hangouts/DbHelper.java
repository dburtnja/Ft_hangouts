package com.example.ezburde.ft_hangouts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ContactsDB";
    private static final int    DB_VER = 4;

    public static final String  TABLE_NAME = "Contacts";
    public static final String  CONT_ID = "_id";
    public static final String  CONT_NAME = "Name";
    public static final String  CONT_PHONE = "Phone";
    public static final String  CONT_PHOTO = "Photo";

    private SQLiteDatabase  db;
    private Cursor          cursor;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  query;

        query = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT NOT NULL, " +
                "%s TEXT NOT NULL, " +
                "%s TEXT)", TABLE_NAME, CONT_ID, CONT_NAME, CONT_PHONE, CONT_PHOTO);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String  query;

        query = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(query);
        onCreate(db);
    }

    private ContentValues createContentValues(String name, String phone, String photo) {
        ContentValues   contentValues;

        contentValues = new ContentValues();
        contentValues.put(CONT_NAME, name);
        contentValues.put(CONT_PHONE, phone);
        contentValues.put(CONT_PHOTO, photo);
        return contentValues;
    }

    public void insertContact(String name, String phone, String photo) {
        SQLiteDatabase  db;
        ContentValues   contentValues;

        db = this.getWritableDatabase();
        contentValues = this.createContentValues(name, phone, photo);
        db.insertWithOnConflict(TABLE_NAME, null, contentValues,
                SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void updateContact(int id, String name, String phone, String photo) {
        SQLiteDatabase  db;
        ContentValues   contentValues;

        db = this.getWritableDatabase();
        contentValues = this.createContentValues(name, phone, photo);
        db.update(TABLE_NAME, contentValues, "_id="+id, null);
        db.close();
    }

    public void updateContact(ContactModel contactModel) {
        SQLiteDatabase  db;
        ContentValues   contentValues;

        db = this.getWritableDatabase();
        contentValues = this.createContentValues(contactModel.getName(),
                contactModel.getPhone(),
                contactModel.getPhoto());
        db.update(TABLE_NAME, contentValues, "_id="+contactModel.getId(), null);
        db.close();
    }

    public void deleteContact(int id) {
        SQLiteDatabase  db;

        db = this.getWritableDatabase();
        db.delete(TABLE_NAME,  CONT_ID + "=?", new String[]{Integer.toString(id)});
        db.close();
    }

    private ContactModel getContact(Cursor cursor) {
        int                 nameIndex;
        int                 phoneIndex;
        int                 photoIndex;
        int                 idIndex;

        nameIndex = cursor.getColumnIndex(CONT_NAME);
        phoneIndex = cursor.getColumnIndex(CONT_PHONE);
        photoIndex = cursor.getColumnIndex(CONT_PHOTO);
        idIndex = cursor.getColumnIndex(CONT_ID);
        return new ContactModel(
                cursor.getInt(idIndex),
                cursor.getString(nameIndex),
                cursor.getString(phoneIndex),
                cursor.getString(photoIndex)
        );
    }

    public List<ContactModel> getContactsList() {
        List<ContactModel>  list;

        list = new LinkedList<>();
        cursor = getCursor();
        while (cursor.moveToNext()) {
            list.add(this.getContact(cursor));
        }
        closeConnection();
        return list;
    }

    private Cursor getCursor() {
        this.db = this.getReadableDatabase();
        this.cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return this.cursor;
    }

    private void closeConnection() {
        this.cursor.close();
        this.db.close();
    }
}
