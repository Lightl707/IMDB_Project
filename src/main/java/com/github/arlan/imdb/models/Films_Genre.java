package com.github.arlan.imdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "films_genre")
public class Films_Genre {
    @DatabaseField(generatedId = true,columnName = "id")
    private int id;
    @DatabaseField(columnName = "genre_id")
    private int genre_id;
    @DatabaseField(columnName = "films_id")
    private int films_id;

    public void setId(int id) {
        this.id = id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getId() {
        return id;
    }

    public void setFilms_id(int films_id) {
        this.films_id = films_id;
    }

    public int getFilms_id() {
        return films_id;
    }

    Films_Genre() {
    }
    Films_Genre(int id,int genre_id,int films_id) {
        this.films_id = films_id;
        this.id = id;
        this.genre_id = genre_id;
    }
}
