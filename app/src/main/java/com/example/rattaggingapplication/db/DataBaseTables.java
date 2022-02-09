package com.example.rattaggingapplication.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseTables {


    private static final String SQL_DATABASE_USERS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS users (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " firstName TEXT," +
                    " lastName TEXT," +
                    " email TEXT," +
                    " phone TEXT," +
                    " city TEXT," +
                    " feedback INTEGER," +
                    " username TEXT," +
                    " password TEXT," +
                    " accessType INTEGER);";

    private static final String SQL_DATABASE_RATS =
            // not visible only for admin- part visible for other users
            "CREATE TABLE IF NOT EXISTS rats (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " userId INTEGER," +
                    " name TEXT," +
                    " dateOfBirth TEXT," +
                    " sex INTEGER," +
                    " numOfTags INTEGER DEFAULT NULL);";

    private static final String SQL_DATABASE_EVENTS =
            // not visible only for admin- part visible for other users
            "CREATE TABLE IF NOT EXISTS events (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " userId INTEGER," +
                    " date TEXT," +
                    " name TEXT);";

    private static final String SQL_DATABASE_ACCESS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS access (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " name TEXT);";

    private static final String SQL_DATABASE_TAGS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS tags (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " eventId INTEGER," +
                    " fileName TEXT," +
                    " emotions INTEGER," +
                    " description TEXT);";

    private static final String SQL_DATABASE_RATS_EVENTS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS ratEvents (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " ratId INTEGER," +
                    " eventId INTEGER);";

    private static final String SQL_DATABASE_EMOTIONS =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS emotions (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " name TEXT);";

    private static final String SQL_DATABASE_SEX =
            // visible only for admin
            "CREATE TABLE IF NOT EXISTS sex (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " name TEXT);";

    public  void createTables(SQLiteDatabase db){
        db.execSQL(SQL_DATABASE_USERS);
        db.execSQL(SQL_DATABASE_RATS);
        db.execSQL(SQL_DATABASE_EVENTS);
        db.execSQL(SQL_DATABASE_ACCESS);
        db.execSQL(SQL_DATABASE_TAGS);
        db.execSQL(SQL_DATABASE_RATS_EVENTS);
        db.execSQL(SQL_DATABASE_EMOTIONS);
        db.execSQL(SQL_DATABASE_SEX);
    }
    private  void dropTables(SQLiteDatabase db){

    }


}
