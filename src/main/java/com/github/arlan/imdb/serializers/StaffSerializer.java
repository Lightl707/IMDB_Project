package com.github.arlan.imdb.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.arlan.imdb.models.Staff;
import com.github.arlan.imdb.models.User;

import java.io.IOException;

public class StaffSerializer extends StdSerializer<Staff> {
    public StaffSerializer() {
        super(Staff.class);
    }

    @Override
    public void serialize(Staff staff, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("fname", staff.getFname());
        jsonGenerator.writeStringField("lname", staff.getLname());
        jsonGenerator.writeStringField("email", staff.getDateOfBirthday());
        jsonGenerator.writeStringField("email", staff.getPicture());
        jsonGenerator.writeEndObject();
    }
}