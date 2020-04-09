package com.github.arlan.imdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "staff_films")
public class Staff_Films {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "staff_id")
    private int staff_id;
    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true, columnName = "films_id")
    private int films_id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public void setFilms_id(int films_id) {
        this.films_id = films_id;
    }

    public int getFilms_id() {
        return films_id;
    }

    public int getStaff_id() {
        return staff_id;
    }
    Staff_Films() {
    }
    Staff_Films(int id,int staff_id,int films_id) {
        this.films_id = films_id;
        this.id = id;
        this.staff_id = staff_id;
    }
}
