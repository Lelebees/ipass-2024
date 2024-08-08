package nl.lelebees.boekmanager.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(RolesAllowedDynamicFeature.class);
//        register(JerseyObjectMapperProvider.class);
        packages("nl.lelebees.boekmanager.manager.api", "nl.lelebees.boekmanager.security", "nl.lelebees.boekmanager.hello.api");
    }
}
