package com.github.arlan.imdb.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.arlan.imdb.DatabaseConfiguration;
import com.github.arlan.imdb.Service;
import com.github.arlan.imdb.models.Film;
import com.github.arlan.imdb.models.Staff;
import io.javalin.http.Context;

import java.io.IOException;
import java.sql.SQLException;

public class StaffController {
    public static void addStaff(Context ctx) throws IOException, SQLException {
        String json = ctx.body();
        Staff staff;
        ObjectMapper obMap = Service.createObjectMapper(false, Staff.class);
        staff = obMap.readValue(json, Staff.class);
        DatabaseConfiguration.staffDao.create(staff);
    }
    public static void getAllStaff(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Staff.class);
        ctx.result(om.writeValueAsString(DatabaseConfiguration.staffDao.queryForAll()));
    }
    public static void getByIdStaff(Context ctx) throws JsonProcessingException, SQLException {
        ObjectMapper om = Service.createObjectMapper(true, Staff.class);
        int id = Integer.parseInt(ctx.pathParam("id"));
        for(Staff st: DatabaseConfiguration.staffDao.queryForAll()) {
            if (st.getId()==id) {
                ctx.result(om.writeValueAsString(st));
            }
        }
        ctx.status(401);
    }
    public static void patchStaff(Context ctx) throws IOException, SQLException {
        String json = ctx.body();
        Staff staff;
        ObjectMapper obMap = Service.createObjectMapper(false, Staff.class);
        staff = obMap.readValue(json, Staff.class);
        DatabaseConfiguration.staffDao.update(staff);
    }
    public static void deleteStaff(Context ctx) throws SQLException {
        int id = Integer.parseInt(ctx.pathParam("id"));
        DatabaseConfiguration.staffDao.deleteById(id);
        ctx.status(204);
    }
}
