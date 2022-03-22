package com.sbrf.reboot.serializeutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class JSONUtils {
    private JSONUtils() {}
    private static final JsonMapper jsonMapper = new JsonMapper();

    public static String toJSON(final Request request) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(request);
    }

    public static String toJSON(final Response response) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(response);
    }

    public static Request JSONtoRequest(final String jsonString) throws JsonProcessingException {
        return jsonMapper.readValue(
                jsonString,
                Request.class
        );
    }

    public static Response JSONtoResponse(final String JsonString) throws JsonProcessingException {
        return jsonMapper.readValue(
                JsonString,
                Response.class
        );
    }
}
