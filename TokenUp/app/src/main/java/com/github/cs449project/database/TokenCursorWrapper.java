package com.github.cs449project.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.github.cs449project.Token;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.github.cs449project.database.TokenDBSchema.*;

/**
 * Created by Al on 2/6/2018.
 * With help of Android Programming book.
 */

public class TokenCursorWrapper extends CursorWrapper{
    public TokenCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Token getToken() {
        String uuidString = getString(getColumnIndex(TokenTable.Cols.UUID));
        String tokenName = getString(getColumnIndex(TokenTable.Cols.TOKENNAME));
        String imgFile = getString(getColumnIndex(TokenTable.Cols.IMG));
        String type = getString(getColumnIndex(TokenTable.Cols.TYPE));
        String subType = getString(getColumnIndex(TokenTable.Cols.SUBTYPE));
        String set = getString(getColumnIndex(TokenTable.Cols.SET));
        String artist = getString(getColumnIndex(TokenTable.Cols.ARTIST));
        String colorsStr = getString(getColumnIndex(TokenTable.Cols.COLORS));
        String tagsStr = getString(getColumnIndex(TokenTable.Cols.TAGS));

        colorsStr = colorsStr.substring(1, colorsStr.length()-1);
        tagsStr = tagsStr.substring(1, tagsStr.length()-1);

        List<String> colors = Arrays.asList(colorsStr.split(", "));
        List<String> tags = Arrays.asList(tagsStr.split(", "));

        Token token = new Token(UUID.fromString(uuidString));
        token.setTokenName(tokenName);
        token.setImgFile(imgFile);
        token.setType(type);
        token.setSubType(subType);
        token.setSet(set);
        token.setArtist(artist);
        token.setColors(colors);
        token.setTags(tags);

        return token;
    }
}
