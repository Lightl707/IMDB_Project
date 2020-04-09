package com.github.arlan.imdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.arlan.imdb.models.Comment;
import com.github.arlan.imdb.models.Film;
import com.github.arlan.imdb.models.Role;
import com.github.arlan.imdb.models.User;
import org.mindrot.jbcrypt.BCrypt;

import com.j256.ormlite.dao.Dao;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;
import java.sql.SQLException;

public class Service {
    public static String encryption(String password) {
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
        return hashed;
    }

    public static boolean authentication(Context ctx) throws SQLException {
        boolean check=false;
        String userName = ctx.basicAuthCredentials().getUsername();
        String userPas = ctx.basicAuthCredentials().getPassword();
        for (User us: DatabaseConfiguration.userDao.queryForAll()) {
            if (us.getFname().equals(userName)  && BCrypt.checkpw(userPas, us.getPassword())) {
                check=true;
            }
        }
        return check;
    }

    public static Role authorization(Context ctx) throws SQLException {
        if (searchUser(ctx).getRole()==Role.ADMIN) return Role.ADMIN;
        else return Role.USER;

    }

    public static User searchUser(Context ctx) throws SQLException {
        String userName = ctx.basicAuthCredentials().getUsername();
        User user=null;
        for (User us : DatabaseConfiguration.userDao.queryForAll()) {
            if (us.getFname().equals(userName)) {
                user=us;
            }
        }
        if (user!=null)
            return user;
        else {
            ctx.status(404);
            throw new RuntimeException();
        }
    }

    public static Film searchFilm(int idFilm, Context ctx) throws SQLException {
        Film film=null;
        for (Film fl: DatabaseConfiguration.filmDao.queryForAll()) {
            if (fl.getId()==idFilm) {
                film=fl;
            }
        }
        if (film!=null)
            return film;
        else {
            ctx.status(404);
            throw new RuntimeException();
        }
    }
    public static Comment searchComment(int idComment, Context ctx) throws SQLException {
        Comment comment=null;
        for (Comment cm: DatabaseConfiguration.commentDao.queryForAll()) {
            if (cm.getId()==idComment) {
                comment=cm;
            }
        }
        if (comment!=null)
            return comment;
        else {
            ctx.status(404);
            throw new RuntimeException();
        }
    }



    public static ObjectMapper createObjectMapper(boolean deserialORserial, Class<?> nameClass) {
        SimpleModule sm = new SimpleModule();
        ObjectMapper om = new ObjectMapper();
        if (deserialORserial)
            return forSerialize(om, sm, nameClass);
        else
            return forDeserialize(om, sm, nameClass);

    }

    public static ObjectMapper forSerialize(ObjectMapper om, SimpleModule sm, Class<?> nameClass) {
        if (User.class.equals(nameClass)) {
            sm.addSerializer(User.class, new UserSerializer());
        } else if (Post.class.equals(nameClass)) {
            sm.addSerializer(Post.class, new PostSerializer());
        } else if (Comment.class.equals(nameClass)) {
            sm.addSerializer(Comment.class,new CommentSerializer());
        }
        return om.registerModule(sm);
    }
    public static ObjectMapper forDeserialize(ObjectMapper om, SimpleModule sm, Class<?> nameClass) {
        if (User.class.equals(nameClass)) {
            sm.addDeserializer(User.class, new UserDeserializer());
        } else if (Post.class.equals(nameClass)) {
            sm.addDeserializer(Post.class, new PostDeserializer());
        } else if (Comment.class.equals(nameClass)) {
            sm.addDeserializer(Comment.class, new CommentDeserializer());
        }
        return om.registerModule(sm);

    }

}


