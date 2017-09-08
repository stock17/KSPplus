package com.yurima.ksp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        KSPSong song = new KSPSong();
        String songText1 =
                            "Полковник Васин приехал на фронт\n" +
                            "Со своей молодой женой\n" +
                            "Полковник Васин собрал свой полк\n" +
                            "И сказал им : \"Пойдем домой.\"\n" +
                            "Мы ведем войну уже 70 лет, \n" +
                            "И нас учили, что жизнь - это бой\n" +
                            "По новым данным разведки\n" +
                            "Мы воевали сами с собой\n";

        song.setText(songText1);
        song.setTitle("Полковник Васин");
        song.setVerseChords(KSPChord.D, KSPChord.Em, KSPChord.G, KSPChord.D, KSPChord.Dsus4, KSPChord.D);


        TextView songTitleView = (TextView) findViewById(R.id.text_view_song_title);
        TextView accordsView = (TextView) findViewById(R.id.text_view_accords);
        TextView songTextView = (TextView) findViewById(R.id.text_view_song_text);

        songTextView.setText(song.getText());
        songTitleView.setText(song.getTitle());
        accordsView.setText(song.getVerseChords());
    }
}
