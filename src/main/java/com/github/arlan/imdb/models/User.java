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

    @DatabaseField(columnName = "login")
    private  String login;
    @DatabaseField(columnName = "password")
    private String password;

    @DatabaseField(columnName = "role")
    private Role role;

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

    public Role getRole() {return role;}

    public void setRole(Role role) {this.role = role;}

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public User() {
    }

    public User(int id, String fname, String lname, String email,String login,String password,Role role) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.login = login;
        this.email = email;
        this.role = role;
        this.password = password;
    }
}