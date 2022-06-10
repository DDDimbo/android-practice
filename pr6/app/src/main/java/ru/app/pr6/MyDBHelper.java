package ru.app.pr6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public MyDBHelper(@Nullable Context context) {
        super(context, MyDBContract.MyDBEntry.TABLE_NAME, (db, masterQuery, editTable, query) -> null, DATABASE_VERSION);
        database = context.openOrCreateDatabase(MyDBContract.MyDBEntry.TABLE_NAME + ".db", Context.MODE_PRIVATE, null);
        database.execSQL(MyDBContract.MyDBEntry.CREATE_TABLE);
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    private SQLiteDatabase database;

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
