package com.example.managersms.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;

public class BlacklistDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "blacklist.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "blacklist";
    private static final String COL_PHONE = "phone";
    private static BlacklistDatabaseHelper instance;

    public static synchronized BlacklistDatabaseHelper getInstance(Context context) {
        if (instance == null) instance = new BlacklistDatabaseHelper(context);
        return instance;
    }

    private BlacklistDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_PHONE + " TEXT PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addToBlacklist(String phone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PHONE, phone);
        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void removeFromBlacklist(String phone) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COL_PHONE + " = ?", new String[]{phone});
    }

    public boolean isBlacklisted(String phone) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COL_PHONE + " = ?", new String[]{phone}, null, null, null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
}