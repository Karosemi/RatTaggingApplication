package com.example.rattaggingapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.rattaggingapplication.db.tables.DataBaseUsers;
import com.example.rattaggingapplication.db.tables.FeedAccess;
import com.example.rattaggingapplication.db.tables.FeedEmotions;
import com.example.rattaggingapplication.db.tables.FeedUsers;
import com.example.rattaggingapplication.register.User;

public class SqlDataBaseHandler extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "rats";

    public SqlDataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final DataBaseTables dbTables = new DataBaseTables();
        dbTables.createTables(db);

        if (dbTables.IsEmpty(db, FeedEmotions.FeedEntryEmotions.TABLE_NAME)){
            addDataToTableEmotions(db);
        }
        if (dbTables.IsEmpty(db, FeedAccess.FeedEntryAccess.TABLE_NAME)){
            addDataToTableAccess(db);
        }
        createUserAdmin(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }



    private void createUserAdmin(SQLiteDatabase db){
        DataBaseUsers dbUsers = new DataBaseUsers();
        User adminUser = dbUsers.getUserAdmin();

        System.out.println(adminUser.getUserName());

        int accessId = this.getAccessId(db, "full");

        adminUser.setAccessType(accessId);
        String[] columnsToReturn = {FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME};
        String[] selectionArgs = { adminUser.getUserName() };

        Cursor resultCursor = this.getValuesFromTable(db, FeedUsers.FeedEntryUsers.TABLE_NAME,
                                    columnsToReturn, FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME,
                                        selectionArgs);
        if (resultCursor.getCount()==0){
            this.addUser(db, adminUser);
        }
    }

    private void addUser(SQLiteDatabase db, User user){
        ContentValues values = new ContentValues();
        values.put(FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME, user.getUserName());
        values.put(FeedUsers.FeedEntryUsers.COLUMN_NAME_PASSWORD, user.getPassword());
        values.put(FeedUsers.FeedEntryUsers.COLUMN_NAME_ACCESS_TYPE, user.getAccessType());
        values.put(FeedUsers.FeedEntryUsers.COLUMN_NAME_EMAIL, user.getEmail());
        values.put(FeedUsers.FeedEntryUsers.COLUMN_NAME_FEEDBACK, user.getFeedBack());
        values.put(FeedUsers.FeedEntryUsers.COLUMN_NAME_FIRST_NAME, user.getFirstName());
        values.put(FeedUsers.FeedEntryUsers.COLUMN_NAME_LAST_NAME, user.getLastName());
        values.put(FeedUsers.FeedEntryUsers.COLUMN_NAME_CITY, user.getCity());
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedUsers.FeedEntryUsers.TABLE_NAME);
        db.insert(FeedUsers.FeedEntryUsers.TABLE_NAME, null, values);

    }




    public Integer getAccessId(SQLiteDatabase db, String accessType){
        String[] columnsToReturn = {FeedAccess.FeedEntryAccess._ID};
        String[] selectionArgs = { accessType };
        Cursor resultCursor = this.getValuesFromTable(db, FeedAccess.FeedEntryAccess.TABLE_NAME,
                                columnsToReturn, FeedAccess.FeedEntryAccess.COLUMN_NAME_NAME,
                selectionArgs);

        resultCursor.moveToFirst();
        return resultCursor.getInt(0);

    }

    public Cursor getValuesFromTable(SQLiteDatabase db, String tableName, String[] colsToReturn, String columnSelection, String[] matchedValues){
        String selection = columnSelection + " =?";
        return db.query(tableName, colsToReturn, selection, matchedValues, null, null, null);
    }



    private void addDataToTableEmotions(SQLiteDatabase db){
        String positive = "positive";
        String negative = "negative";
        String neutral = "neutral";
        ContentValues values = new ContentValues();
        values.put(FeedEmotions.FeedEntryEmotions.COLUMN_NAME_NAME, positive);
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedEmotions.FeedEntryEmotions.TABLE_NAME);
        db.insert(FeedEmotions.FeedEntryEmotions.TABLE_NAME, null, values);
        values.put(FeedEmotions.FeedEntryEmotions.COLUMN_NAME_NAME, negative);
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedEmotions.FeedEntryEmotions.TABLE_NAME);
        db.insert(FeedEmotions.FeedEntryEmotions.TABLE_NAME, null, values);
        values.put(FeedEmotions.FeedEntryEmotions.COLUMN_NAME_NAME, neutral);

        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedEmotions.FeedEntryEmotions.TABLE_NAME);
        db.insert(FeedEmotions.FeedEntryEmotions.TABLE_NAME, null, values);


    }

    private void addDataToTableAccess(SQLiteDatabase db){
        String full = "full";
        String restricted = "restricted";
        ContentValues values = new ContentValues();
        values.put(FeedAccess.FeedEntryAccess.COLUMN_NAME_NAME, full);
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedAccess.FeedEntryAccess.TABLE_NAME);
        db.insert(FeedAccess.FeedEntryAccess.TABLE_NAME, null, values);
        values.put(FeedAccess.FeedEntryAccess.COLUMN_NAME_NAME, restricted);

        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedAccess.FeedEntryAccess.TABLE_NAME);
        db.insert(FeedAccess.FeedEntryAccess.TABLE_NAME, null, values);


    }
}
