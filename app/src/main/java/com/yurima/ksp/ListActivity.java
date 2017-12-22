package com.yurima.ksp;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.yurima.ksp.adapters.KSPSongCursorAdapter;
import com.yurima.ksp.data.KSPSongContract;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;


public class ListActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 1;
    private KSPSongCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.song_list_view);
        mCursorAdapter = new KSPSongCursorAdapter(this, null);
        listView.setAdapter(mCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.setData(ContentUris.withAppendedId(KSPSongContract.KSPSongEntry.CONTENT_URI,id));
                startActivity(intent);
            }
        });

        getSupportLoaderManager().initLoader(LOADER_ID, null, this);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = new String[] {
                KSPSongContract.KSPSongEntry._ID,
                KSPSongContract.KSPSongEntry.COLUMN_TITLE,
                KSPSongContract.KSPSongEntry.COLUMN_ARTIST,
                KSPSongContract.KSPSongEntry.COLUMN_TEXT
        };

        return new CursorLoader(this, KSPSongContract.KSPSongEntry.CONTENT_URI,
                projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
