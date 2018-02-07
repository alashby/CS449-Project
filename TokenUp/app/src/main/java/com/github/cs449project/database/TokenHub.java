package com.github.cs449project.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.cs449project.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.github.cs449project.database.TokenDBSchema.*;

/**
 * Created by Al on 2/6/2018.
 * With help of Android Programming book.
 */

public class TokenHub {
    private static TokenHub sTokenHub;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static TokenHub get(Context context) {
        if (sTokenHub == null) {
            sTokenHub = new TokenHub(context);
        }
        return sTokenHub;
    }

    private TokenHub(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new TokenDBHelper(mContext).getWritableDatabase();
    }

    public void addToken(Token t) {
        ContentValues values = getContentValues(t);

        mDatabase.insert(TokenTable.NAME, null, values);
    }

    public List<Token> getTokens() {
        List<Token> tokens = new ArrayList<>();

        TokenCursorWrapper cursor = queryTokens(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                tokens.add(cursor.getToken());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

        return tokens;
    }

    public Token getToken(UUID id) {
        TokenCursorWrapper cursor = queryTokens(
                TokenTable.Cols.UUID + " =?",
                new String[] { id.toString() }
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getToken();
        }
        finally {
            cursor.close();
        }
    }

    public void updateToken(Token token) {
        String uuidString = token.getId().toString();
        ContentValues values = getContentValues(token);

        mDatabase.update(TokenTable.NAME, values,
                TokenTable.Cols.UUID + " =?",
                new String[] { uuidString });
    }

    private TokenCursorWrapper queryTokens(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                TokenTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new TokenCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Token token) {
        ContentValues values = new ContentValues();
        values.put(TokenTable.Cols.UUID, token.getId().toString());
        values.put(TokenTable.Cols.TOKENNAME, token.getTokenName());
        values.put(TokenTable.Cols.IMG, token.getImgFile());
        values.put(TokenTable.Cols.TYPE, token.getType());
        values.put(TokenTable.Cols.SUBTYPE, token.getSubType());
        values.put(TokenTable.Cols.SET, token.getSet());
        values.put(TokenTable.Cols.ARTIST, token.getArtist());
        values.put(TokenTable.Cols.COLORS, token.getColors().toString());
        values.put(TokenTable.Cols.TAGS, token.getTags().toString());

        return values;
    }
}
