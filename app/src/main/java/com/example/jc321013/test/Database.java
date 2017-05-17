package com.example.jc321013.test;

/**
 * Created by jc321013 on 14/05/2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jc321013 on 14/05/2017.
 */

public class Database extends SQLiteOpenHelper {
    // this class stores the users highscores in an sql database


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
