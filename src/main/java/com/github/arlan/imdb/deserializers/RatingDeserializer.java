package com.github.arlan.imdb.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.arlan.imdb.DatabaseConfiguration;
import com.github.arlan.imdb.models.Comment;
import com.github.arlan.imdb.models.Rating;
import com.github.arlan.imdb.models.Staff;

import java.io.IOException;
import java.sql.SQLException;

public class RatingDeserializer extends StdDeserializer<Rating> {

    public RatingDeserializer() {
        super(Rating.class);
    }

    @Override
    public Rating deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Rating rating = new Rating();
        JsonNode node = parser.getCodec().readTree(parser);
        int id = node.get("id").asInt();
        rating.setId(id);
        int comment_id = node.get("comment_id").asInt();
        try {
            Comment comment = null;
            for (Comment cm : DatabaseConfiguration.commentDao.queryForAll()) {
                if (cm.getId() == comment_id) {
                    comment = cm;
                }
            }
            if (comment != null)
                rating.setComment_id(comment_id);
            else throw new RuntimeException();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        int number = node.get("number").asInt();
        rating.setNumber(number);
        return rating;
    }
}