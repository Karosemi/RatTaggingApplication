package com.example.rattaggingapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class DbManager{
     private SqlDataBaseHandler handler = null;
    private static final String TAG = "DatabaseHelper";

    public DbManager(Context context) {
        handler = new SqlDataBaseHandler(context);

    }
    public void addOneTag(Tag tag){
        addTag(tag);
    }

    private void addTag(Tag tag){
        SQLiteDatabase db = handler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedTags.FeedEntryTags.COLUMN_NAME_EMOTIONS, tag.getEmotions());
        values.put(FeedTags.FeedEntryTags.COLUMN_NAME_DESCRIPTION, tag.getDescription());
        values.put(FeedTags.FeedEntryTags.COLUMN_NAME_FILENAME, tag.getFlename());
        values.put(FeedTags.FeedEntryTags.COLUMN_NAME_EVENT_ID, tag.getEventId());
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedTags.FeedEntryTags.TABLE_NAME);
        db.insert(FeedTags.FeedEntryTags.TABLE_NAME, null, values);
    }

    public void addRatEvent(int ratId, int tagId){
        SQLiteDatabase db = handler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedRatEvents.FeedEntryRatEvents.COLUMN_NAME_TAG_ID, tagId);
        values.put(FeedRatEvents.FeedEntryRatEvents.COLUMN_NAME_RAT_ID, ratId);
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedRatEvents.FeedEntryRatEvents.TABLE_NAME);
        db.insert(FeedRatEvents.FeedEntryRatEvents.TABLE_NAME, null, values);
    }

    public Cursor getLastRowId(String tableName){
        SQLiteDatabase db = handler.getReadableDatabase();
//        String sql = "SELECT _ID FROM " + tableName + " ORDER BY _ID DESC LIMIT 1;";
        String[] columnsToReturn = {FeedEmotions.FeedEntryEmotions._ID};
        return db.query(tableName, columnsToReturn,null, null, null, null, "_ID DESC", "1");
//        return db.rawQuery(sql, null);
    }
    public void addStandardUser(User user){
        SQLiteDatabase db = handler.getWritableDatabase();
        int accessId = this.getAccessId("restricted");
        user.setAccessType(accessId);
        addUser(user);
    }
    public void addOneEvent(Event event){
        addEvent(event);
    }

    private void addEvent(Event event){
        SQLiteDatabase db = handler.getWritableDatabase();
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
        SQLiteDatabase db = handler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedRats.FeedEntryRats.COLUMN_NAME_NAME, rat.getName());
        values.put(FeedRats.FeedEntryRats.COLUMN_NAME_DATE_OF_BIRTH, rat.getDateOfBirth());
        values.put(FeedRats.FeedEntryRats.COLUMN_NAME_USER_ID, rat.getuserId());
        values.put(FeedRats.FeedEntryRats.COLUMN_NAME_SEX, rat.getRatSex());
        Log.d(TAG, "addDataToInfoTable: Adding data to table " + FeedRats.FeedEntryRats.TABLE_NAME);
        db.insert(FeedRats.FeedEntryRats.TABLE_NAME, null, values);

    }
    //TODO create trigger, removing records from database
    private void addUser(User user){
        SQLiteDatabase db = handler.getWritableDatabase();
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

        resultCursor.moveToFirst();
        return resultCursor.getInt(0);

    }

    public Cursor getTwoValuesFromTable(String tableName, String[] colsToReturn, String firstColumnSelection,
                                        String SecondColumnSelection, String[] matchedValues){
        SQLiteDatabase db = handler.getReadableDatabase();
        String selection = firstColumnSelection + " =? AND "+SecondColumnSelection+"=?";
        return db.query(tableName, colsToReturn, selection, matchedValues, null, null, null);
    }

    public Cursor getValuesFromTable(String tableName, String[] colsToReturn, String columnSelection, String[] matchedValues){
        SQLiteDatabase db = handler.getReadableDatabase();
        String selection = columnSelection + " =?";
        return db.query(tableName, colsToReturn, selection, matchedValues, null, null, null);
    }

}
