package com.github.arlan.imdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.neovisionaries.i18n.LanguageCode;

@DatabaseTable(tableName = "language_films")
public class Language {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "ISO-639")
    private static String LanguageCode;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static void setLanguageCode(String languageCode) {
        LanguageCode = languageCode;
    }

    public static String getLanguageCode() {
        return LanguageCode;
    }
    Language() {
    }
    Language(int id,String LanguageCode) {
        this.LanguageCode = LanguageCode;
        this.id = id;
    }
}
