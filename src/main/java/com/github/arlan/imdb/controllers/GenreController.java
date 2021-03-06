package com.github.arlan.imdb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.arlan.imdb.DatabaseConfiguration;
import com.github.arlan.imdb.Service;
import com.github.arlan.imdb.models.Genre;
import com.github.arlan.imdb.models.Role;
import com.github.arlan.imdb.models.Staff;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;

public class GenreController {
    public static void addGenre(Context ctx) throws IOException, SQLException {
        if (Service.authentication(ctx)) {
            String json = ctx.body();
            Genre genre;
            ObjectMapper obMap = Service.createObjectMapper(false, Genre.class);
            genre = obMap.readValue(json, Genre.class);
            if (Service.authorization(ctx) == Role.ADMIN) {
                DatabaseConfiguration.genreDao.create(genre);
            } else {
                ctx.status(403);
            }
        } else {
            ctx.status(401);
        }
    }

    public static void getAllGenre(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Genre.class);
        ctx.result(om.writeValueAsString(DatabaseConfiguration.genreDao.queryForAll()));
    }

    public static void getByIdGenre(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Staff.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        for (Genre gn : DatabaseConfiguration.genreDao.queryForAll()) {
            if (gn.getId() == id) {
                ctx.result(om.writeValueAsString(gn));
            }
        }
        ctx.status(401);
    }

    public static void patchGenre(Context ctx) throws IOException, SQLException {
        if (Service.authentication(ctx)) {
            String json = ctx.body();
            Genre genre;
            ObjectMapper obMap = Service.createObjectMapper(false, Genre.class);
            genre = obMap.readValue(json, Genre.class);
            if (Service.authorization(ctx) == Role.ADMIN) {
                DatabaseConfiguration.genreDao.update(genre);
            } else {
                ctx.status(403);
            }
        } else {
            ctx.status(401);
        }
    }

    public static void deleteGenre(Context ctx) throws SQLException {
        if (Service.authentication(ctx)) {
            int id = Integer.parseInt(ctx.pathParam("id"));
            if (Service.authorization(ctx) == Role.ADMIN) {
                DatabaseConfiguration.genreDao.deleteById(id);
                ctx.status(204);
            }
            else {
                ctx.status(403);
            }
        }
        else {
            ctx.status(401);
        }
    }
}
