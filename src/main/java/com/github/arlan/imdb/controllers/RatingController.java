package com.github.arlan.imdb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.arlan.imdb.DatabaseConfiguration;
import com.github.arlan.imdb.Service;
import com.github.arlan.imdb.models.Film;
import com.github.arlan.imdb.models.Rating;
import com.github.arlan.imdb.models.Role;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;

public class RatingController {
    public static void addRating(Context ctx) throws IOException, SQLException {
        if (Service.authentication(ctx)) {
            String json = ctx.body();
            Rating rating;
            ObjectMapper obMap = Service.createObjectMapper(false, Rating.class);
            rating = obMap.readValue(json, Rating.class);
            DatabaseConfiguration.ratingDao.create(rating);
        } else {
            ctx.status(401);
        }
    }
    public static void getAllRatings(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Rating.class);
        ctx.result(om.writeValueAsString(DatabaseConfiguration.ratingDao.queryForAll()));
    }
    public static void getByIdRating(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Rating.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        for(Rating rt: DatabaseConfiguration.ratingDao.queryForAll()) {
            if (rt.getId()==id) {
                ctx.result(om.writeValueAsString(rt));
            }
        }
        ctx.status(401);
    }
    public static void patchRating(Context ctx) throws IOException, SQLException {
        if (Service.authentication(ctx)) {
            if (Service.authorization(ctx) == Role.ADMIN) {
                String json = ctx.body();
                Rating rating;
                ObjectMapper obMap = Service.createObjectMapper(false, Rating.class);
                rating = obMap.readValue(json, Rating.class);
                DatabaseConfiguration.ratingDao.update(rating);
            }
            else {
                ctx.status(403);
            }
        }
        else {
            ctx.status(401);
        }
    }
    public static void deleteRating(Context ctx) throws SQLException {
        if (Service.authentication(ctx)) {
            if (Service.authorization(ctx) == Role.ADMIN) {
                int id = Integer.parseInt(ctx.pathParam("id"));
                DatabaseConfiguration.ratingDao.deleteById(id);
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
