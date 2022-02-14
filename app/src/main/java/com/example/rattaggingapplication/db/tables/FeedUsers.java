package com.example.rattaggingapplication.db.tables;

import android.provider.BaseColumns;

public class FeedUsers {
    private FeedUsers(){}
    public static class FeedEntryUsers implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_FIRST_NAME = "first_name";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONE = "phone";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_FEEDBACK = "feedback";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_ACCESS_TYPE = "access_type";
    }
}
