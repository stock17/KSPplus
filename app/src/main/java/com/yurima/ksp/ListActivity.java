package com.yurima.ksp;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yurima.ksp.adapters.KSPSongCursorAdapter;
import com.yurima.ksp.data.KSPSongContract;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = (ListView) findViewById(R.id.song_list_view);
        KSPSongCursorAdapter adapter = new KSPSongCursorAdapter(this, null);
        listView.setAdapter(adapter);
    }


}
