package com.github.arlan.imdb;

import com.github.arlan.imdb.controllers.*;
import com.github.arlan.imdb.models.Comment;
import com.github.arlan.imdb.models.Genre;
import com.github.arlan.imdb.models.Staff;
import com.github.arlan.imdb.models.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.Javalin;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> userList = new ArrayList<User>();
        Javalin app = Javalin.create().start(22896);
        app.post("/users", ctx -> UserController.add(ctx));
        app.get("/users", ctx -> UserController.getAll(ctx));
        app.get("/users/:id", ctx -> UserController.getById(ctx));
        app.delete("/users/:id", context -> UserController.delete(context));
        app.patch("/users", context -> UserController.patch(context));

        app.post("/film", context -> FilmController.addFilm(context));
        app.get("/film", context -> FilmController.getAllFilms(context));
        app.get("/film/:id", context -> FilmController.getByIdFilms(context));
        app.delete("/film/:id", context -> FilmController.deleteFilm(context));
        app.patch("/film", context -> FilmController.patchFilm(context));

        app.post("/comment", context -> CommentController.addComment(context));
        app.get("/comment", context -> CommentController.getAllC(context));
        app.get("/comment/:id", context -> CommentController.getByIdC(context));
        app.delete("/comment/:id", context -> CommentController.deleteC(context));
        app.patch("/comment", context -> CommentController.patchComment(context));

        app.post("/genre", context -> GenreController.addGenre(context));
        app.get("/genre", context -> GenreController.getAllGenre(context));
        app.get("/genre/:id", context -> GenreController.getByIdGenre(context));
        app.delete("/genre/:id", context -> GenreController.deleteGenre(context));
        app.patch("/genre", context -> GenreController.patchGenre(context));

        app.post("/staff", context -> StaffController.addStaff(context));
        app.get("/staff", context -> StaffController.getAllStaff(context));
        app.get("/staff/:id", context -> StaffController.getByIdStaff(context));
        app.delete("/staff/:id", context -> StaffController.deleteStaff(context));
        app.patch("/staff", context -> StaffController.patchStaff(context));

        app.post("/rating", context -> RatingController.addRating(context));
        app.get("/rating", context -> RatingController.getAllRatings(context));
        app.get("/rating/:id", context -> RatingController.getByIdRating(context));
        app.delete("/rating/:id", context -> RatingController.deleteRating(context));
        app.patch("/rating", context -> RatingController.patchRating(context));
    }
}

