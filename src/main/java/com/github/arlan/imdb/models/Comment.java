package com.github.arlan.imdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "comment")
public class Comment {
    @DatabaseField(id = true, columnName = "id")
    private int id;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "author")
    private User authorC;
    @DatabaseField(columnName = "text")
    private String textC;

    public Comment() {
    }

    public Comment(int id, User authorC, String textC) {
        this.authorC = authorC;
        this.textC = textC;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorC(User authorC) {
        this.authorC = authorC;
    }

    public String getTextC() {
        return textC;
    }

    public User getAuthorC() {
        return authorC;
    }

    public void setTextC(String textC) {
        this.textC = textC;
    }
}