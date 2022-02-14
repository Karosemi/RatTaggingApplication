package com.example.rattaggingapplication.db.tables;

import android.provider.BaseColumns;

public class FeedRatEvents {
    private FeedRatEvents(){}
    public static class FeedEntryRatEvents implements BaseColumns {
        public static final String TABLE_NAME = "rat_events";
        public static final String COLUMN_NAME_RAT_ID = "rat_id";
        public static final String COLUMN_NAME_TAG_ID = "tag_id";
    }
}
