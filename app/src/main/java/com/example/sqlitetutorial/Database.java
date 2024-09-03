package com.example.sqlitetutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RANDOM_RECORDS";
    public static final String TABLE_NAME = "RANDOM_DATA";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PASSWORD";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " NAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public boolean insertData(String name, String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_3, email);
        cv.put(COL_4, password);

        long var = database.insert(TABLE_NAME, null, cv);
        if (var == -1)
            return false;
        else
            return true;

    }
    public boolean isUserLoggedIn(String email, String password) {
        SQLiteDatabase database = this.getReadableDatabase();
        String[] columns = {COL_1};
        String selection = COL_3 + "=? AND " + COL_4 + "=?";
        String[] selectionArgs = {email, password};

        Cursor cursor = database.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();

        cursor.close();
        database.close();

        return count > 0;
    }

    public boolean checkUser(String password, String username) {
        SQLiteDatabase database = this.getWritableDatabase();
        String[] columns = {COL_1};
        String selection = COL_4 + "=?" + " and " + COL_2 + "=?";
        String[] selectionArgs = {password, username};
        Cursor cursor = database.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        database.close();
        cursor.close();
        if (count > 0) {
            return true;
        } else
            return false;


    }


}
