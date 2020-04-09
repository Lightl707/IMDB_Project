package com.github.arlan.imdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "genre")
public class Genre {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String name;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    Genre() {
    }
    Genre(int id,String name) {
        this.id = id;
        this.name = name;
    }
}