package com.github.arlan.imdb.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.IOException;

@DatabaseTable(tableName = "user")
public class User {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "fname")
    private String fname;

    @DatabaseField(columnName = "lname")
    private String lname;

    @DatabaseField(columnName = "email")
    private String email;

    @DatabaseField(columnName = "password")
    private String password;

    @DatabaseField(columnName = "role")
    private Enum role;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFname() {
        return fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Enum getRole() {return role;}

    public void setRole(Enum role) {this.role = role;}

    public User() {
    }

    public User(int id, String fname, String lname, String email,String password,Enum role) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}