package com.github.arlan.imdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "language_films")
public class Language {
    @DatabaseField(generatedId = true)
    private int id;
}
