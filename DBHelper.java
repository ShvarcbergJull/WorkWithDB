package com.example.pc.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "myjava.db";
    final static String TABLE_NAME = "shopping_list";
    final static String CREATE = "CREATE TABLE " + TABLE_NAME + "(`_id` INTEGER PRIMARY KEY AUTOINCREMENT, `product` TEXT NOT NULL, `cost` INTEGER NOT NULL );";

    private static final int DATABASE_VERSION = 10;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE);
        db.execSQL("INSERT INTO shopping_list (`product`, `cost`) VALUES ('Milk', 32);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // выполняется, если версия базы данных отличается
        db.execSQL("DROP DATABASE "+DB_NAME);
        this.onCreate(db);
    }
}
