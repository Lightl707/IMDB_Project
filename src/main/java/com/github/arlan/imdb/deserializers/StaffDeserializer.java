package com.github.arlan.imdb.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.arlan.imdb.Service;
import com.github.arlan.imdb.models.Role;
import com.github.arlan.imdb.models.Staff;
import com.github.arlan.imdb.models.User;

import java.io.IOException;

public class StaffDeserializer extends StdDeserializer<Staff> {

    public StaffDeserializer() {
        super(Staff.class);
    }

    @Override
    public Staff deserialize(JsonParser parser, DeserializationContext deserializationContext) throws IOException {

        Staff staff = new Staff();
        JsonNode node = parser.getCodec().readTree(parser);

        staff.setId(node.get("id").asInt());
        staff.setFname(node.get("fname").asText());
        staff.setLname(node.get("lname").asText());
        staff.setEmail(node.get("email").asText());
        staff.setDateOfBirthday(node.get("dateOfBirthday").asText());
        staff.setPicture(node.get("picture").asText());
        return staff;
    }
}

