package com.example.finalprojectcst2335;

import android.provider.BaseColumns;

public final class FavoritesContract {

    private FavoritesContract() {
        // Private constructor to prevent instantiation
    }

    public static class FavoritesEntry implements BaseColumns {
        public static final String TABLE_NAME = "favorites";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_HEADLINE = "headline";
        public static final String COLUMN_URL = "url";
    }
}
