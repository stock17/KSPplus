package com.yurima.ksp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yurima.ksp.R;
import com.yurima.ksp.data.KSPSongContract;

public class KSPSongCursorAdapter extends CursorAdapter {


    public KSPSongCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvTitle = (TextView) view.findViewById(R.id.text_view_song_title);
        TextView tvArtist = (TextView) view.findViewById(R.id.text_view_song_artist);

        int titleIndex = cursor.getColumnIndex(KSPSongContract.KSPSongEntry.COLUMN_TITLE);
        int artistIndex = cursor.getColumnIndex(KSPSongContract.KSPSongEntry.COLUMN_ARTIST);

        String strTitle = cursor.getString(titleIndex);
        String strArtist = cursor.getString(artistIndex);

        tvTitle.setText(strTitle);
        tvArtist.setText(strArtist);

    }
}
