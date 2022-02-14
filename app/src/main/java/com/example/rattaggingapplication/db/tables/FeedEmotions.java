package com.example.rattaggingapplication.db.tables;

import android.provider.BaseColumns;

public class FeedEmotions {
    private FeedEmotions(){}
    public static class FeedEntryEmotions implements BaseColumns {
        public static final String TABLE_NAME = "emotions";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
