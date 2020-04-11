package com.github.arlan.imdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "film")
public class Film {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "genre_id")
    private int genre_id;
    @DatabaseField
    private String description;
    @DatabaseField
    private int year;
    @DatabaseField
    private int language_id;
    @DatabaseField
    private int staff_id;
    @DatabaseField
    private int rating_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(int language_id) {
        this.language_id = language_id;
    }

    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public Film() {
    }
    Film(int id,int genre_id,String description,int year,int language_id,int staff_id,int rating_id) {
        this.id = id;
        this.genre_id = genre_id;
        this.description = description;
        this.year = year;
        this.language_id = language_id;
        this.staff_id = staff_id;
        this.rating_id = rating_id;
    }
}
