package com.github.arlan.imdb.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.arlan.imdb.models.Genre;
import com.github.arlan.imdb.models.Staff;

import java.io.IOException;

public class GenreDeserializer extends StdDeserializer<Genre> {

    public GenreDeserializer() {
        super(Genre.class);
    }

    @Override
    public Genre deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {

        Genre genre = new Genre();
        JsonNode node = parser.getCodec().readTree(parser);

        genre.setId(node.get("id").asInt());
        genre.setName(node.get("name").asText());
        return genre;
    }
}