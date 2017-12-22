package com.yurima.ksp.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static android.R.attr.id;


public class KSPProvider extends ContentProvider {

    private static KSPDbHelper mDbHelper;

    private static final int SONGS = 100;
    private static final int SONG_ID = 101;

    private static final UriMatcher sUriMatcher;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(KSPSongContract.CONTENT_AUTHORITY,
                KSPSongContract.PATH_KSPSongs, SONGS);
        sUriMatcher.addURI(KSPSongContract.CONTENT_AUTHORITY,
                KSPSongContract.PATH_KSPSongs + "/#", SONG_ID);
    }

    //TODO: Notification


    @Override
    public boolean onCreate() {
        mDbHelper = new KSPDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case SONGS :
                cursor = db.query(KSPSongContract.KSPSongEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case SONG_ID:
                selection = KSPSongContract.KSPSongEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(KSPSongContract.KSPSongEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            default:
                throw new IllegalArgumentException();
        }

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SONGS :
                return KSPSongContract.KSPSongEntry.CONTENT_LIST_TYPE;
            case SONG_ID:
                return KSPSongContract.KSPSongEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);

        switch (match) {
            case SONGS:
                long id = db.insert(KSPSongContract.KSPSongEntry.TABLE_NAME, null, values);
                return ContentUris.withAppendedId(uri, id);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int numberOfRows;
        switch (match) {
            case SONGS :
                numberOfRows = db.delete(KSPSongContract.KSPSongEntry.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            case SONG_ID:
                selection = KSPSongContract.KSPSongEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                numberOfRows = db.delete(KSPSongContract.KSPSongEntry.TABLE_NAME,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException();
        }

        return numberOfRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);
        int numberOfRows;
        switch (match) {
            case SONGS :
                numberOfRows = db.update(KSPSongContract.KSPSongEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            case SONG_ID:
                selection = KSPSongContract.KSPSongEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                numberOfRows = db.update(KSPSongContract.KSPSongEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            default:
                throw new IllegalArgumentException();
        }

        return numberOfRows;
    }
}
