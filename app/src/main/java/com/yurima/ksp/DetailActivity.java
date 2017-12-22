package com.yurima.ksp;

import android.content.ContentUris;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.yurima.ksp.data.KSPSongContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.widget.TextView;

import com.yurima.ksp.data.KSPSongContract;

public class DetailActivity extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView mArtist;
    private TextView mTitle;
    private TextView mText;

    private Uri mCurrentSongUri;

    private static final int DETAIL_LOADER_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mArtist = (TextView) findViewById(R.id.detail_artist);
        mTitle = (TextView) findViewById(R.id.detail_title);
        mText = (TextView) findViewById(R.id.detail_song_text);

        mCurrentSongUri = getIntent().getData();

        getSupportLoaderManager().initLoader(DETAIL_LOADER_ID, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        long songId = ContentUris.parseId(mCurrentSongUri);
        if (songId == -1) {
            throw new IllegalArgumentException();
        } else {
            String[] projection = new String[]{
                    KSPSongContract.KSPSongEntry.COLUMN_TITLE,
                    KSPSongContract.KSPSongEntry.COLUMN_ARTIST,
                    KSPSongContract.KSPSongEntry.COLUMN_TEXT
            };
            return new CursorLoader(this, mCurrentSongUri, projection, null, null, null);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        int titleIndex = cursor.getColumnIndex(KSPSongContract.KSPSongEntry.COLUMN_TITLE);
        int artistIndex = cursor.getColumnIndex(KSPSongContract.KSPSongEntry.COLUMN_ARTIST);
        int textIndex    = cursor.getColumnIndex(KSPSongContract.KSPSongEntry.COLUMN_TEXT);

        cursor.moveToFirst();

        mTitle.setText(cursor.getString(titleIndex));
        mArtist.setText(cursor.getString(artistIndex));
        mText.setText(cursor.getString(textIndex));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mTitle.setText("");
        mArtist.setText("");
        mText.setText("");
    }
}
