package com.yurima.ksp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.yurima.ksp.data.KSPSongContract.KSPSongEntry;


public class KSPDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "KSPSongs.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + KSPSongEntry.TABLE_NAME + "(" +
            KSPSongEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KSPSongEntry.COLUMN_TITLE + "TEXT NOT NULL, " +
            KSPSongEntry.COLUMN_ATHOR + "TEXT NOT NULL" +
            KSPSongEntry.COLUMN_TEXT + "TEXT";

    private static final String SQL_DELETE_TABLE = "DROP TABLE " + KSPSongEntry.TABLE_NAME;

    public KSPDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }
}
