package com.github.arlan.imdb;

import com.github.arlan.imdb.models.*;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseConfiguration {

    public static ConnectionSource connectionSource;
    public static Dao<User, Integer> userDao;
    public static Dao<Comment, Integer> commentDao;
    public static Dao<Film, Integer> filmDao;
    public static Dao<Staff, Integer> staffDao;
    public static Dao<Rating, Integer> ratingDao;
    public static Dao<Genre, Integer> genreDao;

    static {
        try {
            connectionSource = new JdbcConnectionSource("jdbc:sqlite://C:\\Users\\User\\Desktop\\imdb.db");
            TableUtils.createTableIfNotExists(connectionSource, User.class);
            TableUtils.createTableIfNotExists(connectionSource, Comment.class);
            userDao = DaoManager.createDao(DatabaseConfiguration.connectionSource, User.class);
            commentDao = DaoManager.createDao(DatabaseConfiguration.connectionSource, Comment.class);
            filmDao = DaoManager.createDao(DatabaseConfiguration.connectionSource,Film.class);
            staffDao = DaoManager.createDao(DatabaseConfiguration.connectionSource,Staff.class);
            ratingDao = DaoManager.createDao(DatabaseConfiguration.connectionSource,Rating.class);
            genreDao = DaoManager.createDao(DatabaseConfiguration.connectionSource,Genre.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}