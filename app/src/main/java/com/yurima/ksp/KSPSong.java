package com.yurima.ksp;

import java.util.ArrayList;
import java.util.Arrays;

class KSPSong {

    private String mText;
    private String mTitle;
    private String mArtist;
    private ArrayList<KSPChord> mVerseChords = new ArrayList<>();

    String getVerseChords() {
        StringBuilder sb = new StringBuilder();
        for (KSPChord chord : mVerseChords) {
            sb.append(chord).append(" ");
        }
        return sb.toString();
    }

    void setVerseChords(KSPChord... chords) {
        mVerseChords.addAll(Arrays.asList(chords));
    }

    String getText() {
        return mText;
    }

    void setText(String text) {
        mText = text;
    }

    String getTitle() {
        return mTitle;
    }

    void setTitle(String title) {
        mTitle = title;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }


    KSPSong(){

    }


}
