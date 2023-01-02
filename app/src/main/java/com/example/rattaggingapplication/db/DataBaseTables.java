package com.example.rattaggingapplication.db;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import com.example.rattaggingapplication.db.tables.FeedAccess;
import com.example.rattaggingapplication.db.tables.FeedEmotions;
import com.example.rattaggingapplication.db.tables.FeedEvents;
import com.example.rattaggingapplication.db.tables.FeedRatEvents;
import com.example.rattaggingapplication.db.tables.FeedRats;
import com.example.rattaggingapplication.db.tables.FeedTags;
import com.example.rattaggingapplication.db.tables.FeedUsers;


public class DataBaseTables {
    private static final String SQL_DATABASE_USERS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS " + FeedUsers.FeedEntryUsers.TABLE_NAME + " (" +
                    FeedUsers.FeedEntryUsers._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedUsers.FeedEntryUsers.COLUMN_NAME_FIRST_NAME +" TEXT," +
                    FeedUsers.FeedEntryUsers.COLUMN_NAME_LAST_NAME +" TEXT," +
                    FeedUsers.FeedEntryUsers.COLUMN_NAME_EMAIL +" TEXT," +
                    FeedUsers.FeedEntryUsers.COLUMN_NAME_CITY +" TEXT,"+
                    FeedUsers.FeedEntryUsers.COLUMN_NAME_PHONE +" TEXT,"+
                    FeedUsers.FeedEntryUsers.COLUMN_NAME_FEEDBACK +" INTEGER," +
                    FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME +" TEXT," +
                    FeedUsers.FeedEntryUsers.COLUMN_NAME_PASSWORD +" TEXT," +
                    FeedUsers.FeedEntryUsers.COLUMN_NAME_ACCESS_TYPE +" INTEGER);";

    private static final String SQL_DATABASE_RATS =
            // not visible only for admin- part visible for other users
            "CREATE TABLE IF NOT EXISTS " + FeedRats.FeedEntryRats.TABLE_NAME + " (" +
                    FeedRats.FeedEntryRats._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedRats.FeedEntryRats.COLUMN_NAME_USER_ID +" INTEGER," +
                    FeedRats.FeedEntryRats.COLUMN_NAME_NAME +" TEXT," +
                    FeedRats.FeedEntryRats.COLUMN_NAME_DATE_OF_BIRTH +" TEXT," +
                    FeedRats.FeedEntryRats.COLUMN_NAME_SEX +" TEXT," +
                    FeedRats.FeedEntryRats.COLUMN_NAME_NUM_OF_TAGS +" INTEGER DEFAULT NULL);";


    private static final String SQL_DATABASE_EVENTS =
            // not visible only for admin- part visible for other users
            "CREATE TABLE IF NOT EXISTS " + FeedEvents.FeedEntryEvents.TABLE_NAME + " (" +
                    FeedEvents.FeedEntryEvents._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedEvents.FeedEntryEvents.COLUMN_NAME_USER_ID +" INTEGER," +
                    FeedEvents.FeedEntryEvents.COLUMN_NAME_DATE +" TEXT," +
                    FeedEvents.FeedEntryEvents.COLUMN_NAME_NAME +" TEXT);";

    private static final String SQL_DATABASE_ACCESS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS " + FeedAccess.FeedEntryAccess.TABLE_NAME + " (" +
                    FeedAccess.FeedEntryAccess._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedAccess.FeedEntryAccess.COLUMN_NAME_NAME +" TEXT);";

    private static final String SQL_DATABASE_TAGS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS " + FeedTags.FeedEntryTags.TABLE_NAME + " (" +
                    FeedTags.FeedEntryTags._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedTags.FeedEntryTags.COLUMN_NAME_EVENT_ID +" INTEGER," +
                    FeedTags.FeedEntryTags.COLUMN_NAME_FILENAME +" TEXT," +
                    FeedTags.FeedEntryTags.COLUMN_NAME_EMOTIONS +" INTEGER," +
                    FeedTags.FeedEntryTags.COLUMN_NAME_DESCRIPTION +" TEXT);";


    private static final String SQL_DATABASE_RATS_EVENTS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS " + FeedRatEvents.FeedEntryRatEvents.TABLE_NAME + " (" +
                    FeedRatEvents.FeedEntryRatEvents._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedRatEvents.FeedEntryRatEvents.COLUMN_NAME_RAT_ID +" INTEGER," +
                    FeedRatEvents.FeedEntryRatEvents.COLUMN_NAME_TAG_ID +" INTEGER);";

    private static final String SQL_DATABASE_EMOTIONS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS " + FeedEmotions.FeedEntryEmotions.TABLE_NAME + " (" +
                    FeedEmotions.FeedEntryEmotions._ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedEmotions.FeedEntryEmotions.COLUMN_NAME_NAME +" TEXT);";

    private static final String SQL_DELETE_EMOTIONS_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEmotions.FeedEntryEmotions.TABLE_NAME;

    private static final String SQL_DELETE_ACCESS_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedAccess.FeedEntryAccess.TABLE_NAME;

    private static final String SQL_DELETE_USERS_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedUsers.FeedEntryUsers.TABLE_NAME;

    private static final String SQL_DELETE_RATS_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedRats.FeedEntryRats.TABLE_NAME;
    private static final String SQL_DELETE_RAT_EVENTS_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedRatEvents.FeedEntryRatEvents.TABLE_NAME;
    private static final String SQL_DELETE_EVENTS_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEvents.FeedEntryEvents.TABLE_NAME;
    private static final String SQL_DELETE_TAGS_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedTags.FeedEntryTags.TABLE_NAME;

    public boolean IsEmpty(SQLiteDatabase db, String tableName){
        long NoOfRows = DatabaseUtils.queryNumEntries(db,tableName);
        return NoOfRows == 0;
    }




    public void createTables(SQLiteDatabase db){
        db.execSQL(SQL_DATABASE_USERS);
        db.execSQL(SQL_DATABASE_RATS);
        db.execSQL(SQL_DATABASE_EVENTS);
        db.execSQL(SQL_DATABASE_ACCESS);
        db.execSQL(SQL_DATABASE_TAGS);
        db.execSQL(SQL_DATABASE_RATS_EVENTS);
        db.execSQL(SQL_DATABASE_EMOTIONS);


    }
    public void dropTables(SQLiteDatabase db){
        db.execSQL(SQL_DELETE_ACCESS_ENTRIES);
        db.execSQL(SQL_DELETE_EMOTIONS_ENTRIES);
        db.execSQL(SQL_DELETE_USERS_ENTRIES);
        db.execSQL(SQL_DELETE_RATS_ENTRIES);
        db.execSQL(SQL_DELETE_RAT_EVENTS_ENTRIES);
        db.execSQL(SQL_DELETE_TAGS_ENTRIES);
        db.execSQL(SQL_DELETE_EVENTS_ENTRIES);
    }


}
