package com.ojdgaf.examples.hibernateapp.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

public class JsonLogger implements Logger {
    private Logger logger;
    private ObjectMapper mapper;

    public JsonLogger(Logger logger) {
        this.logger = logger;
        this.mapper = new ObjectMapper();
        mapper.registerModule(new Hibernate5Module());
    }

    @Override
    public void log(Object o) throws Exception {
        logger.log(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o));
    }
}
