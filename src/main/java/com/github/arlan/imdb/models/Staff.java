package com.github.arlan.imdb.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "staff")
public class Staff {
    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String fname;
    @DatabaseField
    private String lname;
    @DatabaseField
    private String email;
    @DatabaseField
    private String dateOfBirthday;
    @DatabaseField
    private String picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(String dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }
    public Staff(){
    }
    Staff(int id,String fname,String lname,String email,String dateOfBirthday,String picture) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.dateOfBirthday = dateOfBirthday;
        this.picture = picture;
    }
}
