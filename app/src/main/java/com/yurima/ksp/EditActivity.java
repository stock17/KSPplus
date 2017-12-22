package com.yurima.ksp;

import android.content.ContentValues;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mTitle = (EditText) findViewById(R.id.edit_title);
        mArtist = (EditText) findViewById(R.id.edit_artist);
        mText = (EditText) findViewById(R.id.edit_text);



        Button saveButton = (Button) findViewById(R.id.edit_button_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put(KSPSongContract.KSPSongEntry.COLUMN_ARTIST, mArtist.getText().toString());
                cv.put(KSPSongContract.KSPSongEntry.COLUMN_TITLE, mTitle.getText().toString());
                cv.put(KSPSongContract.KSPSongEntry.COLUMN_TEXT, mText.getText().toString());
                getContentResolver().insert(KSPSongContract.KSPSongEntry.CONTENT_URI, cv);
                finish();
            }
        });
    }
}
