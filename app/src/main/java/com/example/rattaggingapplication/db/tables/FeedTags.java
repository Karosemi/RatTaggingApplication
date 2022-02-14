package com.example.rattaggingapplication.db.tables;

import android.provider.BaseColumns;

public class FeedTags {
    private FeedTags(){}
    public static class FeedEntryTags implements BaseColumns {
        public static final String TABLE_NAME = "tags";
        public static final String COLUMN_NAME_EVENT_ID = "event_id";
        public static final String COLUMN_NAME_FILENAME = "filename";
        public static final String COLUMN_NAME_EMOTIONS = "emotions";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
    }
}
