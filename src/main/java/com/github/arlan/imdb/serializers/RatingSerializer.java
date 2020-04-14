package com.github.arlan.imdb.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.arlan.imdb.models.Film;
import com.github.arlan.imdb.models.Rating;

import java.io.IOException;

public class RatingSerializer extends StdSerializer<Rating> {
    RatingSerializer() { super(Rating.class); }

    @Override
    public void serialize(Rating rating, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumberField("id",rating.getId());
        jsonGenerator.writeNumberField("number",rating.getNumber());
        jsonGenerator.writeNumberField("comment_id",rating.getComment_id());
        jsonGenerator.writeEndObject();
    }
}
