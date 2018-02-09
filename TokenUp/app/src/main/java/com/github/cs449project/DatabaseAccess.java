package com.github.cs449project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.sort;

/**
 * Created by Al on 2/7/2018.
 * Reference: http://www.javahelps.com/2015/04/import-and-use-external-database-in.html
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;


    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }


    public void open() {
        this.database = openHelper.getWritableDatabase();
    }


    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<Token> getTokens() {
        List<Token> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Token token = new Token();
            token.setId(cursor.getString(0));
            token.setTokenName(cursor.getString(1));
            token.setImgFile(cursor.getString(2));
            token.setType(cursor.getString(3));
            token.setSubType(cursor.getString(4));
            token.setSet(cursor.getString(5));
            token.setArtist(cursor.getString(6));
            token.setColors(cursor.getString(7));
            token.setTags(cursor.getString(8));
            list.add(token);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getIds() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getNames() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String name = cursor.getString(1);
            if (!list.contains(name)) {
                list.add(name);
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getTypes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String type = cursor.getString(3);
            if (!list.contains(type)) {
                list.add(type);
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getSubTypes() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String subtype = cursor.getString(4);
            if (!list.contains(subtype)) {
                list.add(subtype);
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getSets() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String set = cursor.getString(5);
            if (!list.contains(set)) {
                list.add(set);
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getArtists() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String artist = cursor.getString(6);
            if (!list.contains(artist)) {
                list.add(artist);
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getColors() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String colorcombo = cursor.getString(7);
            String colors = "";

            char[] cols = colorcombo.toCharArray();
            Arrays.sort(cols);
            colorcombo = new String(cols);

            for (int i = 0; i < colorcombo.length(); i++){
                char col = colorcombo.charAt(i);
                switch(col) {
                    case 'R': colors += "Red/"; break;
                    case 'G': colors += "Green/"; break;
                    case 'B': colors += "Black/"; break;
                    case 'U': colors += "Blue/"; break;
                    case 'W': colors += "White/"; break;
                    case 'A': colors += "Artifact"; break;
                    case 'C': colors += "Colorless"; break;
                }
            }
            if (colors.charAt(colors.length()-1) == '/') {
                colors = colors.substring(0, colors.length()-1);
            }
            if (!list.contains(colors)) {
                list.add(colors);
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getTags() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String tags = cursor.getString(8);
            if (tags.length() > 0) {
                List<String> taglist = Arrays.asList(tags.split(","));
                for (int i = 0; i < taglist.size(); i++) {
                    if (!list.contains(taglist.get(i))) {
                        list.add(taglist.get(i));
                    }
                }
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }



    public List<String> getIdsByName(String name) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentname = cursor.getString(1);
            if (name.equals(currentname)) {
                list.add(cursor.getString(0));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getIdsByType(String type) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currenttype = cursor.getString(3);
            if (type.equals(currenttype)) {
                list.add(cursor.getString(0));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getIdsBySubType(String subtype) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentsubtype = cursor.getString(4);
            if (subtype.equals(currentsubtype)) {
                list.add(cursor.getString(0));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getIdsBySet(String set) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentset = cursor.getString(5);
            if (set.equals(currentset)) {
                list.add(cursor.getString(0));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getIdsByArtist(String artist) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentartist = cursor.getString(6);
            if (artist.equals(currentartist)) {
                list.add(cursor.getString(0));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }



    public List<String> getIdsByColors(String colors) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentcombo = cursor.getString(7);
            String currentcolors = "";

            char[] cols = currentcombo.toCharArray();
            Arrays.sort(cols);
            currentcombo = new String(cols);

            for (int i = 0; i < currentcombo.length(); i++){
                char col = currentcombo.charAt(i);
                switch(col) {
                    case 'R': currentcolors += "Red/"; break;
                    case 'G': currentcolors += "Green/"; break;
                    case 'B': currentcolors += "Black/"; break;
                    case 'U': currentcolors += "Blue/"; break;
                    case 'W': currentcolors += "White/"; break;
                    case 'A': currentcolors += "Artifact"; break;
                    case 'C': currentcolors += "Colorless"; break;
                }
            }
            if (currentcolors.charAt(currentcolors.length()-1) == '/') {
                currentcolors = currentcolors.substring(0, currentcolors.length()-1);
            }

            if (colors.equals(currentcolors)) {
                list.add(cursor.getString(0));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getIdsByTag(String tag) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currenttags = cursor.getString(8);
            List<String> currenttaglist = Arrays.asList(currenttags.split(","));
            if (currenttaglist.contains(tag)) {
                    list.add(cursor.getString(0));
                }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getImgsByName(String name) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentname = cursor.getString(1);
            if (name.equals(currentname)) {
                list.add(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getImgsByType(String type) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currenttype = cursor.getString(3);
            if (type.equals(currenttype)) {
                list.add(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);

        return list;
    }

    public List<String> getImgsBySubType(String subtype) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentsubtype = cursor.getString(4);
            if (subtype.equals(currentsubtype)) {
                list.add(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getImgsBySet(String set) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentset = cursor.getString(5);
            if (set.equals(currentset)) {
                list.add(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getImgsByArtist(String artist) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentartist = cursor.getString(6);
            if (artist.equals(currentartist)) {
                list.add(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }



    public List<String> getImgsByColors(String colors) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currentcombo = cursor.getString(7);
            String currentcolors = "";

            char[] cols = currentcombo.toCharArray();
            Arrays.sort(cols);
            currentcombo = new String(cols);

            for (int i = 0; i < currentcombo.length(); i++){
                char col = currentcombo.charAt(i);
                switch(col) {
                    case 'R': currentcolors += "Red/"; break;
                    case 'G': currentcolors += "Green/"; break;
                    case 'B': currentcolors += "Black/"; break;
                    case 'U': currentcolors += "Blue/"; break;
                    case 'W': currentcolors += "White/"; break;
                    case 'A': currentcolors += "Artifact"; break;
                    case 'C': currentcolors += "Colorless"; break;
                }
            }
            if (currentcolors.charAt(currentcolors.length()-1) == '/') {
                currentcolors = currentcolors.substring(0, currentcolors.length()-1);
            }

            if (colors.equals(currentcolors)) {
                list.add(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public List<String> getImgsByTag(String tag) {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM Tokens", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String currenttags = cursor.getString(8);
            List<String> currenttaglist = Arrays.asList(currenttags.split(","));
            if (currenttaglist.contains(tag)) {
                list.add(cursor.getString(2));
            }
            cursor.moveToNext();
        }
        cursor.close();
        sort(list);
        return list;
    }

    public void insertToken(Token token) {
        ContentValues values = new ContentValues();
        values.put("Id", token.getId());
        values.put("Name", token.getTokenName());
        values.put("ImgFile", token.getImgFile());
        values.put("Type", token.getType());
        values.put("SubType", token.getSubType());
        values.put("Set", token.getSet());
        values.put("Artist", token.getArtist());
        values.put("Colors", token.getColors());
        values.put("Tags", token.getTags());
        database.insert("Tokens", null, values);
    }
}
