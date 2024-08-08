package nl.lelebees.boekmanager.jersey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
public class JerseyObjectMapperProvider implements ContextResolver<ObjectMapper> {
    public static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder().addModule(new JavaTimeModule()).build();
//    static {
//        OBJECT_MAPPER.findAndRegisterModules();
//        OBJECT_MAPPER.registerModule(new JavaTimeModule());
//    }

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return OBJECT_MAPPER;
    }
}
