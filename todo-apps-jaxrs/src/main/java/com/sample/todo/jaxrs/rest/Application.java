/**
 * 
 */
package com.sample.todo.jaxrs.rest;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {

    public Application() {
        packages(true, "com.sample");
        register(JacksonFeature.class);
        register(MultiPartFeature.class);
        register(LoggingFilter.class);
    }

}
