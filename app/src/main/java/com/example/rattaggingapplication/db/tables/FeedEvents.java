package com.example.rattaggingapplication.db.tables;

import android.provider.BaseColumns;

public class FeedEvents {
    private FeedEvents(){}
    public static class FeedEntryEvents implements BaseColumns {
        public static final String TABLE_NAME = "events";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_USER_ID = "user_id";
    }
}
