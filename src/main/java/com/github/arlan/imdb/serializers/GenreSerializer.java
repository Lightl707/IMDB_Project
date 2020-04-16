package com.github.arlan.imdb.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.arlan.imdb.models.Genre;
import com.github.arlan.imdb.models.User;

import java.io.IOException;

public class GenreSerializer extends StdSerializer<Genre> {
    public GenreSerializer() {
        super(Genre.class);
    }

    @Override
    public void serialize(Genre genre, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name",genre.getName());
        jsonGenerator.writeEndObject();
    }
}