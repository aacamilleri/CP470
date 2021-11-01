package com.example.androidassignments;

import static android.app.DownloadManager.COLUMN_ID;

import android.util.Log;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChatDatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "items.db";
    static int VERSION_NUM = 1;
    static final String KEY_ID = "KEY_ID";
    static final String KEY_MESSAGE = "KEY_MESSAGE";
    static final String TABLE_NAME = "MESSAGES";
    static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "(" + KEY_ID
            + " integer primary key autoincrement, " + KEY_MESSAGE
            + " text not null);";

    public ChatDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int old, int new1) {
        Log.w(ChatDatabaseHelper.class.getName(),
                "Upgrading database from version " + old + " to "
                        + new1);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(database);
    }
}
