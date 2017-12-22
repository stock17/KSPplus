package com.yurima.ksp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Yury on 15.12.2017.
 */

final public class KSPSongContract {

    private KSPSongContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.yurima.ksp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_KSPSongs = "KSPSongs";


    public static final class KSPSongEntry implements BaseColumns {

        public static final String TABLE_NAME = "KSPSongs";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_KSPSongs);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_KSPSongs;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_KSPSongs;


        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ARTIST = "artist";
        public static final String COLUMN_TEXT = "text";
    }

}
