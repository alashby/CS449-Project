package com.github.cs449project.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.cs449project.database.TokenDBSchema.TokenTable;

/**
 * Created by Al on 2/5/2018.
 * With help of Android Programming book
 */


public class TokenDBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "tokenDB.db";

    public TokenDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TokenTable.NAME + "(" +
        " _id integer primary key autoincrement, " +
        TokenTable.Cols.UUID + ", " +
        TokenTable.Cols.TOKENNAME + ", " +
        TokenTable.Cols.IMG + ", " +
        TokenTable.Cols.TYPE + ", " +
        TokenTable.Cols.SUBTYPE + ", " +
        TokenTable.Cols.SET + ", " +
        TokenTable.Cols.ARTIST + ", " +
        TokenTable.Cols.COLORS + ", " +
        TokenTable.Cols.TAGS +
        ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

