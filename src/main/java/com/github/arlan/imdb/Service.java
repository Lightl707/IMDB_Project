package com.github.arlan.imdb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.arlan.imdb.deserializers.*;
import com.github.arlan.imdb.models.*;
import com.github.arlan.imdb.serializers.*;
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
        boolean check = false;
        String userName = ctx.basicAuthCredentials().getUsername();
        String userPas = ctx.basicAuthCredentials().getPassword();
        for (User us : DatabaseConfiguration.userDao.queryForAll()) {
            if (us.getLogin().equals(userName) && BCrypt.checkpw(userPas, us.getPassword())) {
                check = true;
            }
        }
        return check;
    }

    public static Role authorization(Context ctx) throws SQLException {
        if (searchUser(ctx).getRole() == Role.ADMIN) return Role.ADMIN;
        else return Role.USER;

    }

    public static User searchUser(Context ctx) throws SQLException {
        String userName = ctx.basicAuthCredentials().getUsername();
        User user = null;
        for (User us : DatabaseConfiguration.userDao.queryForAll()) {
            if (us.getLogin().equals(userName)) {
                user = us;
            }
        }
        if (user != null)
            return user;
        else {
            ctx.status(404);
            throw new RuntimeException();
        }
    }

    public static Film searchFilm(int idFilm, Context ctx) throws SQLException {
        Film film = null;
        for (Film fl : DatabaseConfiguration.filmDao.queryForAll()) {
            if (fl.getId() == idFilm) {
                film = fl;
            }
        }
        if (film != null)
            return film;
        else {
            ctx.status(404);
            throw new RuntimeException();
        }
    }

    public static Genre searchGenre(int idGenre,Context ctx) throws SQLException {
        Genre genre = null;
        for (Genre gn : DatabaseConfiguration.genreDao.queryForAll()) {
            if (gn.getId() == idGenre) {
                genre = gn;
            }
        }
        if (genre != null)
            return genre;
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
        } else if (Staff.class.equals(nameClass)) {
            sm.addSerializer(Staff.class, new StaffSerializer());
        } else if (Comment.class.equals(nameClass)) {
            sm.addSerializer(Comment.class,new CommentSerializer());
        } else if (Film.class.equals(nameClass)) {
            sm.addSerializer(Film.class,new FilmSerializer());
        } else if (Genre.class.equals(nameClass)) {
            sm.addSerializer(Genre.class,new GenreSerializer());
        } else if (Rating.class.equals(nameClass)) {
            sm.addSerializer(Rating.class,new RatingSerializer());
        }


        return om.registerModule(sm);
    }
    public static ObjectMapper forDeserialize(ObjectMapper om, SimpleModule sm, Class<?> nameClass) {
        if (User.class.equals(nameClass)) {
            sm.addDeserializer(User.class, new UserDeserializer());
        } else if (Staff.class.equals(nameClass)) {
            sm.addDeserializer(Staff.class, new StaffDeserializer());
        } else if (Comment.class.equals(nameClass)) {
            sm.addDeserializer(Comment.class, new CommentDeserializer());
        } else if (Film.class.equals(nameClass)) {
            sm.addDeserializer(Film.class, new FilmDeserializer());
        } else if (Genre.class.equals(nameClass)) {
            sm.addDeserializer(Genre.class, new GenreDeserializer());
        } else if (Rating.class.equals(nameClass)) {
            sm.addDeserializer(Rating.class, new RatingDeserializer());
        }


        return om.registerModule(sm);

    }
}


