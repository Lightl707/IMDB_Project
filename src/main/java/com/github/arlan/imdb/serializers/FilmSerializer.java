package com.github.arlan.imdb.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.arlan.imdb.models.Film;
import com.github.arlan.imdb.models.User;

import java.io.IOException;

public class FilmSerializer extends StdSerializer<Film> {
    FilmSerializer() { super(Film.class); }

    @Override
    public void serialize(Film film, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("description",film.getDescription());
        jsonGenerator.writeStringField("year",film.getYear());
        jsonGenerator.writeEndObject();
    }

}