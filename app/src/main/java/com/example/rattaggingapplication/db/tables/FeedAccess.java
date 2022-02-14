package com.example.rattaggingapplication.db.tables;

import android.provider.BaseColumns;

public class FeedAccess {
    private FeedAccess(){}
    public static class FeedEntryAccess implements BaseColumns {
        public static final String TABLE_NAME = "access";
        public static final String COLUMN_NAME_NAME = "name";
    }

}
