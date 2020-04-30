package com.github.arlan.imdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "rating")
public class Rating {
    @DatabaseField(generatedId = true,columnName = "id")
    private int id;
    @DatabaseField
    private int number;
    @DatabaseField(columnName = "comment_id")
    private int comment_id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getComment_id() {
        return comment_id;
    }
    public Rating() {
    }
    Rating(int id,int number,int comment_id) {
        this.id = id;
        this.number = number;
        this.comment_id = comment_id;
    }
}
