package com.sonty.pmtl.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sonty on 4/9/14.
 */
public class mySqliteHelper extends SQLiteOpenHelper {
    public mySqliteHelper(Context context) {
        super(context, context.getString(R.string.db_path), null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
