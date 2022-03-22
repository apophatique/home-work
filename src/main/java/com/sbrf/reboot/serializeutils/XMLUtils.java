package com.sbrf.reboot.serializeutils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.sbrf.reboot.dto.Request;
import com.sbrf.reboot.dto.Response;

public class XMLUtils {
    private static final XmlMapper XML_MAPPER = new XmlMapper();

    private XMLUtils() {}

    public static String toXML(final Request request) throws JsonProcessingException {
        return XML_MAPPER.writeValueAsString(request);
    }

    public static String toXML(final Response response) throws JsonProcessingException {
        return XML_MAPPER.writeValueAsString(response);
    }

    public static Request XMLtoRequest(final String xmlString) throws JsonProcessingException {
        return XML_MAPPER.readValue(
                xmlString,
                Request.class
        );
    }

    public static Response XMLtoResponse(final String xmlString) throws JsonProcessingException {
        return XML_MAPPER.readValue(
                xmlString,
                Response.class
        );
    }
}
