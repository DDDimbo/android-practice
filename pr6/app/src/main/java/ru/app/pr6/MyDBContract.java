package ru.app.pr6;

import android.provider.BaseColumns;

public class MyDBContract {
    public MyDBContract() {}

    public static class MyDBEntry implements BaseColumns {
        public static final String TABLE_NAME = "info";
        public static final String NAME = "Name";
        public static final String PHONE = "Phone";
        public static final String DESCRIPTION = "Description";
        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME        + " TEXT,"
                + PHONE       + " TEXT,"
                + DESCRIPTION + " TEXT);";
    }

}
