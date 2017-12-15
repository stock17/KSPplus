package com.yurima.ksp.data;

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

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_ATHOR = "author";
        public static final String COLUMN_TEXT = "text";
    }

}