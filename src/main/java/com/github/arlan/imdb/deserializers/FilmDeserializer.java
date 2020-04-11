package com.github.arlan.imdb.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.arlan.imdb.DatabaseConfiguration;
import com.github.arlan.imdb.Service;
import com.github.arlan.imdb.models.*;

import java.io.IOException;
import java.sql.SQLException;

public class FilmDeserializer extends StdDeserializer<Film> {

    public FilmDeserializer() {
        super(Film.class);
    }

    @Override
    public Film deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Film film = new Film();
        JsonNode node = parser.getCodec().readTree(parser);

        int id = node.get("id").asInt();
        film.setId(id);

        int staff_id = node.get("staff_id").asInt();
        try {
            Staff staff=null;
            for(Staff st: DatabaseConfiguration.staffDao.queryForAll()) {
                if(st.getId()==staff_id) {
                    staff=st;
                }
            }
            if (staff!=null)
                film.setStaff_id(staff_id);
            else throw new RuntimeException();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int rating_id = node.get("rating").asInt();
        try {
            for(Rating rt: DatabaseConfiguration.ratingDao.queryForAll()) {
                if (rt.getId()==rating_id) {
                    film.setRating_id(rating_id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int genre_id = node.get("genre_id").asInt();
        try {
            Genre genre =null;
            for(Genre gn: DatabaseConfiguration.genreDao.queryForAll()) {
                if(gn.getId()==staff_id) {
                    genre=gn;
                }
            }
            if (genre!=null)
                film.setGenre_id(genre_id);
            else throw new RuntimeException();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String description = node.get("description").asText();
        film.setDescription(description);

        int year = node.get("year").asInt();
        film.setYear(year);
        return film;
    }
}
