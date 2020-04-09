package com.github.arlan.imdb.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.arlan.imdb.models.Comment;
import java.io.IOException;

public class CommentSerializer extends StdSerializer<Comment> {

    public CommentSerializer() {
        super(Comment.class);
    }

    @Override
    public void serialize(Comment c, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("id", String.valueOf(c.getId()));
        jsonGenerator.writeStringField("authorC", c.getAuthorC().getFname() + " " + c.getAuthorC().getLname());
        jsonGenerator.writeStringField("textC", c.getTextC());
        jsonGenerator.writeEndObject();
    }
}

