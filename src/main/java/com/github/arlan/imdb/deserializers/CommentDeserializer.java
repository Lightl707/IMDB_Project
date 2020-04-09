package com.github.arlan.imdb.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.arlan.imdb.DatabaseConfiguration;
import com.github.arlan.imdb.models.Comment;
import com.github.arlan.imdb.models.User;

import java.io.IOException;
import java.sql.SQLException;

public class CommentDeserializer extends StdDeserializer<Comment> {

    public CommentDeserializer() {
        super(Comment.class);
    }

    @Override
    public Comment deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Comment comment = new Comment();
        JsonNode node = parser.getCodec().readTree(parser);

        int id = node.get("id").asInt();
        comment.setId(id);

        int authorId = node.get("author").asInt();
        try {
            for(User us: DatabaseConfiguration.userDao.queryForAll()) {
                if (us.getId()==authorId) {
                    comment.setAuthorC(us);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String text = node.get("text").asText();
        comment.setTextC(text);
        return comment;
    }
}
