package com.github.arlan.imdb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.arlan.imdb.DatabaseConfiguration;
import com.github.arlan.imdb.Service;
import com.github.arlan.imdb.models.Comment;
import com.github.arlan.imdb.models.Film;
import com.github.arlan.imdb.models.Role;
import com.github.arlan.imdb.models.User;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;

public class FilmController {
        public static void addFilm(Context ctx) throws IOException, SQLException {
            if (Service.authentication(ctx)) {
                String json = ctx.body();
                Film film;
                ObjectMapper obMap = Service.createObjectMapper(false, Film.class);
                if (Service.authorization(ctx) == Role.ADMIN) {
                    film = obMap.readValue(json, Film.class);
                    DatabaseConfiguration.filmDao.create(film);
                }
                else {
                    ctx.status(403);
            }
        }
        else {
            ctx.status(401);
        }
    }
    public static void getAllFilms(Context ctx) throws JsonProcessingException, SQLException {
            ObjectMapper om = Service.createObjectMapper(true, Film.class);
            ctx.result(om.writeValueAsString(DatabaseConfiguration.filmDao.queryForAll()));
        }
    public static void getByIdFilms(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Film.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
            for(Film fl: DatabaseConfiguration.filmDao.queryForAll()) {
                if (fl.getId()==id) {
                    ctx.result(om.writeValueAsString(fl));
                }
            }
            ctx.status(401);
    }
    public static void getByGenreFilms(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Film.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
            for(Film fl: DatabaseConfiguration.filmDao.queryForAll()) {
                if (fl.getGenre_id()==id) {
                    ctx.result(om.writeValueAsString(fl));
                }
            }
            ctx.status(401);
    }
    public static void getByStaffFilms(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Film.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
            for(Film fl: DatabaseConfiguration.filmDao.queryForAll()) {
                if (fl.getStaff_id()==id) {
                    ctx.result(om.writeValueAsString(fl));
                }
            }
            ctx.status(401);
    }
    public static void getByRatingFilms(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Film.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
            for(Film fl: DatabaseConfiguration.filmDao.queryForAll()) {
                if (fl.getRating_id()==id) {
                    ctx.result(om.writeValueAsString(fl));
                }
            }
    }
    public static void getByYearFilms(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Film.class);
        int year = Integer.parseInt(ctx.pathParam("id"));
            for(Film fl: DatabaseConfiguration.filmDao.queryForAll()) {
                if (fl.getYear() == year) {
                    ctx.result(om.writeValueAsString(fl));
                }
            }
    }
    public static void patchFilm(Context ctx) throws IOException, SQLException {
        if (Service.authentication(ctx)) {
            if (Service.authorization(ctx) == Role.ADMIN) {
                String json = ctx.body();
                Film film;
                ObjectMapper obMap = Service.createObjectMapper(false, Film.class);
                film = obMap.readValue(json, Film.class);
                DatabaseConfiguration.filmDao.update(film);
            }
            else {
                ctx.status(403);
            }
        }
    }
    public static void deleteFilm(Context ctx) throws SQLException {
        if (Service.authentication(ctx)) {
            if (Service.authorization(ctx) == Role.ADMIN) {
                int id = Integer.parseInt(ctx.pathParam("id"));
                DatabaseConfiguration.filmDao.deleteById(id);
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