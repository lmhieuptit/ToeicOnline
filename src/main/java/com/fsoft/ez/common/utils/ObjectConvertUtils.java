package com.fsoft.ez.common.utils;

import java.io.IOException;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author hieutc4
 *
 *         contains all utilities methods about convert object to String and
 *         vice versa
 * 
 *         NOV 27, 2019
 * 
 */
public final class ObjectConvertUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ObjectConvertUtils.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private ObjectConvertUtils() {
        // prevent create
    }

    /**
     * convert object to string
     * 
     * @param object
     * @return String
     */
    public static String convertToString(Object object) {
        String str = null;

        try {
            str = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("JsonProcessingException {0}", e);
        }
        return str;
    }

    /**
     * convert to object type T from String
     * 
     * @param data
     * @param type
     * @return instance of type T
     */
    public static <T> T convertFromString(String data, Class<T> type) {
        T result = null;

        try {
            result = MAPPER.readValue(data, type);
        } catch (IOException e) {
            LOGGER.error("IOException {0}", e);
        }
        return result;
    }

    /**
     * convert to object type T from Object
     * 
     * @param data
     * @param type
     * @return instance of type T
     */
    public static <T> T convertFromObject(Object data, Class<T> type) {
        T result = null;
        if (Objects.nonNull(data)) {
            result = convertFromString(data.toString(), type);
        }
        return result;
    }
}
