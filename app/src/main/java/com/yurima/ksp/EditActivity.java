package com.yurima.ksp;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.yurima.ksp.data.KSPSongContract;

public class EditActivity extends AppCompatActivity {

    private EditText mTitle;
    private EditText mArtist;
    private EditText mText;

    private Uri mCurrentSongUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mCurrentSongUri = getIntent().getData();

        mTitle = (EditText) findViewById(R.id.edit_title);
        mArtist = (EditText) findViewById(R.id.edit_artist);
        mText = (EditText) findViewById(R.id.edit_text);

        if (mCurrentSongUri != null) {
            loadSong();
        }



        Button saveButton = (Button) findViewById(R.id.edit_button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(KSPSongContract.KSPSongEntry.COLUMN_ARTIST, mArtist.getText().toString());
                cv.put(KSPSongContract.KSPSongEntry.COLUMN_TITLE, mTitle.getText().toString());
                cv.put(KSPSongContract.KSPSongEntry.COLUMN_TEXT, mText.getText().toString());

                if (mCurrentSongUri == null)
                    getContentResolver().insert(KSPSongContract.KSPSongEntry.CONTENT_URI, cv);
                else
                    getContentResolver().update(mCurrentSongUri, cv, null, null);
                finish();
            }
        });

        Button cancelButton = (Button) findViewById(R.id.edit_button_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadSong() {
        String[] projection = new String[] {
                KSPSongContract.KSPSongEntry._ID,
                KSPSongContract.KSPSongEntry.COLUMN_TITLE,
                KSPSongContract.KSPSongEntry.COLUMN_ARTIST,
                KSPSongContract.KSPSongEntry.COLUMN_TEXT
        };

        Cursor cursor = getContentResolver().query(mCurrentSongUri,
                projection, null, null, null);

        int titleIndex = cursor.getColumnIndex(KSPSongContract.KSPSongEntry.COLUMN_TITLE);
        int artistIndex = cursor.getColumnIndex(KSPSongContract.KSPSongEntry.COLUMN_ARTIST);
        int textIndex = cursor.getColumnIndex(KSPSongContract.KSPSongEntry.COLUMN_TEXT);

        cursor.moveToFirst();

        mTitle.setText(cursor.getString(titleIndex));
        mArtist.setText(cursor.getString(artistIndex));
        mText.setText(cursor.getString(textIndex));
    }
}
