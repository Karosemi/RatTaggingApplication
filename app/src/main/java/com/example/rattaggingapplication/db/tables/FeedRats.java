package com.example.rattaggingapplication.db.tables;

import android.provider.BaseColumns;

public class FeedRats {
    private FeedRats(){}
    public static class FeedEntryRats implements BaseColumns {
        public static final String TABLE_NAME = "rats";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DATE_OF_BIRTH = "date_of_birth";
        public static final String COLUMN_NAME_SEX = "sex";
        public static final String COLUMN_NAME_NUM_OF_TAGS = "sum_of_tags";
    }
}
