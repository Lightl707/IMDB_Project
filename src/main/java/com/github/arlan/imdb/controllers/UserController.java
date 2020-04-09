package com.github.arlan.imdb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.arlan.imdb.DatabaseConfiguration;
import com.github.arlan.imdb.Service;
import com.github.arlan.imdb.models.Role;
import com.github.arlan.imdb.models.User;
import com.j256.ormlite.dao.Dao;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserController {


    public static void add(Context ctx) throws IOException, SQLException {
        String json = ctx.body();
        User user;
        ObjectMapper obMap = Service.createObjectMapper(false, User.class);
        user = obMap.readValue(json, User.class);
        DatabaseConfiguration.userDao.create(user);
        ctx.status(201);
    }

    public static void getAll(Context ctx) throws JsonProcessingException, SQLException {
        if (Service.authentication(ctx)) {
            ObjectMapper om = Service.createObjectMapper(true, User.class);
            if (Service.authorization(ctx) == Role.ADMIN) {
                ctx.result(om.writeValueAsString(DatabaseConfiguration.userDao.queryForAll()));
            } else if (Service.authorization(ctx) == Role.USER) {
                ctx.result(om.writeValueAsString(DatabaseConfiguration.userDao.queryForAll()));
            }
        } else {
            ctx.status(401);
        }
    }

    public static void getById(Context ctx) throws JsonProcessingException, SQLException {
        if (Service.authentication(ctx)) {
            ObjectMapper om = Service.createObjectMapper(true, User.class);
            int id = Integer.parseInt(ctx.pathParam("id"));
            for (User u : DatabaseConfiguration.userDao.queryForAll()) {
                if (u.getId() == id) {
                    ctx.result(om.writeValueAsString(u));
                }
            }
        } else {
            ctx.status(401);
        }
    }

    public static void patch(Context ctx) throws IOException, SQLException {
        String json = ctx.body();
        User user;
        ObjectMapper obMap = Service.createObjectMapper(false, User.class);
        if (Service.authentication(ctx)) {
            if (Service.authorization(ctx) == Role.ADMIN) {
                user = obMap.readValue(json, User.class);
                DatabaseConfiguration.userDao.update(user);
            } else if (Service.authorization(ctx) == Role.USER) {
                user = obMap.readValue(json, User.class);
                if (user.getId() == Service.searchUser(ctx).getId()) {
                    DatabaseConfiguration.userDao.update(user);
                } else {
                    ctx.status(403);
                }
            }
        } else {
            ctx.status(401);
        }
    }

    public static void delete(Context ctx) throws SQLException {
        if (Service.authentication(ctx)) {
            if (Service.authorization(ctx) == Role.ADMIN) {
                DatabaseConfiguration.userDao.deleteById(Integer.parseInt(ctx.pathParam("id")));
                ctx.status(204);
            } else if (Service.authorization(ctx) == Role.USER) {
                if (Integer.parseInt(ctx.pathParam("id")) == Service.searchUser(ctx).getId()) {
                    DatabaseConfiguration.userDao.deleteById(Integer.parseInt(ctx.pathParam("id")));
                    ctx.status(204);
                } else {
                    ctx.status(403);
                }
            }
        } else {
            ctx.status(401);
        }
    }
}