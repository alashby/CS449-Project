package com.github.cs449project.database;

/**
 * Created by Al on 2/5/2018.
 * With help of Android Programming book.
 */

public class TokenDBSchema {
    public static final class TokenTable {
        public static final String NAME = "tokens";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TOKENNAME = "name";
            public static final String IMG = "img";
            public static final String TYPE = "type";
            public static final String SUBTYPE = "subtype";
            public static final String SET = "set";
            public static final String ARTIST = "artist";
            public static final String COLORS = "colors";
            public static final String TAGS = "tags";
        }
    }
}
