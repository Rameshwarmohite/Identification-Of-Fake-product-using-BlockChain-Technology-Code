package com.phoenixzone.fake_product_detection.utility;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper {

    private final Context mContext;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "bhel.db";
    private static final int DATABASE_VERSION = 1;

    private static final String NOTIFICATION_TABLE = "tbl_notification";


    //create notification table
    private static final String CREATE_NOTIFICATION_TABLE = "CREATE TABLE " + NOTIFICATION_TABLE + " (date_time text," +
            "title text, description text)";


    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_NOTIFICATION_TABLE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {

                db.execSQL("DROP TABLE IF EXISTS " + NOTIFICATION_TABLE);
                onCreate(db);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void Reset() {
        mDbHelper.onUpgrade(this.mDb, 1, 1);
    }

    public DBHelper(Context ctx) {
        mContext = ctx;
        mDbHelper = new DatabaseHelper(mContext);
    }

    public DBHelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        mDbHelper.close();
    }

    //insert notifications
    public void insertNotificationInfo(String date_time, String title, String description) {
        ContentValues cv = new ContentValues();
        cv.put("date_time", date_time);
        cv.put("title", title);
        cv.put("description", description);
        mDb.insert(NOTIFICATION_TABLE, null, cv);
    }


    //get all notifications
    public Cursor getAllNotificationInfo() {
        Cursor res = mDb.rawQuery("SELECT * FROM " + NOTIFICATION_TABLE + "", null);
        return res;
    }

    //delete all notifications
    public void deleteAllNotifications() {
        mDb.execSQL("DELETE FROM " + NOTIFICATION_TABLE);
    }


}