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
import com.example.rattaggingapplication.db.tables.FeedEvents;
import com.example.rattaggingapplication.db.tables.FeedRatEvents;
import com.example.rattaggingapplication.db.tables.FeedRats;
import com.example.rattaggingapplication.db.tables.FeedTags;
import com.example.rattaggingapplication.db.tables.FeedUsers;
import com.example.rattaggingapplication.register.Event;
import com.example.rattaggingapplication.register.Rat;
import com.example.rattaggingapplication.register.Tag;
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
//        dbTables.dropTables(db);
        dbTables.createTables(db);


        if (dbTables.IsEmpty(db, FeedEmotions.FeedEntryEmotions.TABLE_NAME)){
            addDataToTableEmotions();
        }
        if (dbTables.IsEmpty(db, FeedAccess.FeedEntryAccess.TABLE_NAME)){
            addDataToTableAccess();
        }
        createUserAdmin();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }

    public Cursor getColumnDataFromTable( String tableName, String name){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT " + name + " FROM " + tableName+";";

        return db.rawQuery(sql, null);

    }
    public Cursor getDataFromTable( String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM " + tableName+";";

        return db.rawQuery(sql, null);

    }
    public void addOneTag(Tag tag){
        addTag(tag);
    }
    private void addTag(Tag tag){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedTags.FeedEntryTags.COLUMN_NAME_EMOTIONS, tag.getEmotions());
        values.put(FeedTags.FeedEntryTags.COLUMN_NAME_DESCRIPTION, tag.getDescription());
        values.put(FeedTags.FeedEntryTags.COLUMN_NAME_FILENAME, tag.getFlename());
        values.put(FeedTags.FeedEntryTags.COLUMN_NAME_EVENT_ID, tag.getEventId());
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedTags.FeedEntryTags.TABLE_NAME);
        db.insert(FeedTags.FeedEntryTags.TABLE_NAME, null, values);
    }

    public void addRatEvent(int ratId, int tagId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedRatEvents.FeedEntryRatEvents.COLUMN_NAME_TAG_ID, tagId);
        values.put(FeedRatEvents.FeedEntryRatEvents.COLUMN_NAME_RAT_ID, ratId);
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedRatEvents.FeedEntryRatEvents.TABLE_NAME);
        db.insert(FeedRatEvents.FeedEntryRatEvents.TABLE_NAME, null, values);
    }
    private void createUserAdmin(){
        DataBaseUsers dbUsers = new DataBaseUsers();
        User adminUser = dbUsers.getUserAdmin();

        System.out.println(adminUser.getUserName());

        int accessId = this.getAccessId("full");

        adminUser.setAccessType(accessId);
        String[] columnsToReturn = {FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME};
        String[] selectionArgs = { adminUser.getUserName() };

        Cursor resultCursor = getValuesFromTable(FeedUsers.FeedEntryUsers.TABLE_NAME, columnsToReturn, FeedUsers.FeedEntryUsers.COLUMN_NAME_USERNAME,
                selectionArgs);
        if (resultCursor.getCount()==0){
            this.addUser(adminUser);
        }


    }
    public Cursor getLastRowId(String tableName){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT _ID FROM " + tableName + " ORDER BY _ID DESC LIMIT 1;";
        return db.rawQuery(sql, null);
    }
    public void addStandardUser(User user){
        int accessId = this.getAccessId("restricted");
        user.setAccessType(accessId);
        addUser(user);
    }
    public void addOneEvent(Event event){
        addEvent(event);
    }

    private void addEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedEvents.FeedEntryEvents.COLUMN_NAME_NAME, event.getName());
        values.put(FeedEvents.FeedEntryEvents.COLUMN_NAME_DATE, event.getDate());
        values.put(FeedEvents.FeedEntryEvents.COLUMN_NAME_USER_ID, event.getuserId());
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedEvents.FeedEntryEvents.TABLE_NAME);
        db.insert(FeedEvents.FeedEntryEvents.TABLE_NAME, null, values);

    }

    public void addOneRat(Rat rat){
        addRat(rat);
    }
    private void addRat(Rat rat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedRats.FeedEntryRats.COLUMN_NAME_NAME, rat.getName());
        values.put(FeedRats.FeedEntryRats.COLUMN_NAME_DATE_OF_BIRTH, rat.getDateOfBirth());
        values.put(FeedRats.FeedEntryRats.COLUMN_NAME_USER_ID, rat.getuserId());
        values.put(FeedRats.FeedEntryRats.COLUMN_NAME_SEX, rat.getRatSex());
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedRats.FeedEntryRats.TABLE_NAME);
        db.insert(FeedRats.FeedEntryRats.TABLE_NAME, null, values);

    }

    private void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
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


    public Integer getAccessId(String accessType){
        String[] columnsToReturn = {FeedAccess.FeedEntryAccess._ID};
        String[] selectionArgs = { accessType };
        Cursor resultCursor = getValuesFromTable(FeedAccess.FeedEntryAccess.TABLE_NAME, columnsToReturn, FeedAccess.FeedEntryAccess.COLUMN_NAME_NAME,
                selectionArgs);

//        System.out.println("dddhdhdhd" + resultCursor.getCount());

        resultCursor.moveToFirst();
        return resultCursor.getInt(0);

    }

    public Cursor getTwoValuesFromTable(String tableName, String[] colsToReturn, String firstColumnSelection,
                                        String SecondColumnSelection, String[] matchedValues){
        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columnsToReturn = { "column_1", "column_2" };
        String selection = firstColumnSelection + " =? AND "+SecondColumnSelection+"=?";
//        String[] selectionArgs = { someValue }; // matched to "?" in selection
        return db.query(tableName, colsToReturn, selection, matchedValues, null, null, null);
    }

    public Cursor getValuesFromTable(String tableName, String[] colsToReturn, String columnSelection, String[] matchedValues){
        SQLiteDatabase db = this.getReadableDatabase();
//        String[] columnsToReturn = { "column_1", "column_2" };
        String selection = columnSelection + " =?";
//        String[] selectionArgs = { someValue }; // matched to "?" in selection
        return db.query(tableName, colsToReturn, selection, matchedValues, null, null, null);
    }



    private void addDataToTableEmotions(){
        SQLiteDatabase db = this.getWritableDatabase();
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

    private void addDataToTableAccess(){
        SQLiteDatabase db = this.getWritableDatabase();
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
