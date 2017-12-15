package com.yurima.ksp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import com.yurima.ksp.adapters.KSPSongCursorAdapter;


public class ListActivity extends AppCompatActivity {

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
        KSPSongCursorAdapter adapter = new KSPSongCursorAdapter(this, null);
        listView.setAdapter(adapter);
    }


}
