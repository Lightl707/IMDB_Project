package com.github.arlan.imdb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.arlan.imdb.DatabaseConfiguration;
import com.github.arlan.imdb.Service;
import com.github.arlan.imdb.models.Comment;
import com.github.arlan.imdb.models.Role;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;

public class CommentController {
    public static void addComment(Context ctx) throws IOException, SQLException {
        String json =ctx.body();
        Comment comment;
        ObjectMapper obMap = Service.createObjectMapper(false, Comment.class);
        comment = obMap.readValue(json, Comment.class);
        if (Service.authentication(ctx)) {
            if (Service.authorization(ctx)== Role.ADMIN) {
                DatabaseConfiguration.commentDao.create(comment);
            }else if (Service.authorization(ctx)==Role.USER) {
                if (comment.getAuthorC().getId()==Service.searchUser(ctx).getId()) {
                    DatabaseConfiguration.commentDao.create(comment);
                } else {
                    ctx.status(403);
                }
            }
        } else
            ctx.status(401);
    }

    public static void getAllC(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Comment.class);
        if (Service.authentication(ctx)) {
            ctx.result(om.writeValueAsString(DatabaseConfiguration.commentDao.queryForAll()));
        } else
            ctx.status(401);
    }

    public static void getByIdC(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Comment.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        if (Service.authentication(ctx)) {
            for(Comment cm: DatabaseConfiguration.commentDao.queryForAll()) {
                if (cm.getId()==id) {
                    ctx.result(om.writeValueAsString(cm));
                }
            }
        } else
            ctx.status(401);
    }

    public static void patchComment(Context ctx) throws SQLException, IOException {
        String json = ctx.body();
        Comment comment;
        ObjectMapper obMap = Service.createObjectMapper(false, Comment.class);
        comment = obMap.readValue(json, Comment.class);
        if (Service.authentication(ctx)) {
            if (Service.authorization(ctx) == Role.ADMIN)
                DatabaseConfiguration.commentDao.update(comment);
            else if (Service.authorization(ctx) == Role.USER) {
                if (comment.getAuthorC().getId() == Service.searchUser(ctx).getId())
                    DatabaseConfiguration.commentDao.update(comment);
                else
                    ctx.status(403);
            }
        } else
            ctx.status(401);
    }
    public static void deleteC(Context ctx) throws SQLException {
        int id = Integer.parseInt(ctx.pathParam("id"));

        if (Service.authentication(ctx)) {
            if (Service.authorization(ctx) == Role.ADMIN) {
                DatabaseConfiguration.commentDao.deleteById(id);
                ctx.status(204);
            } else if (Service.authorization(ctx) == Role.USER) {
                if (Service.searchComment(id,ctx).getAuthorC().getId() == Service.searchUser(ctx).getId()) {
                    DatabaseConfiguration.commentDao.deleteById(id);
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

