package com.minibank.config;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Ann on 26/09/14.
 */
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig()
    {
        //register(RequestContextFilter.class);
        packages("com.minibank.rest");
        register(LoggingFilter.class);
    }
}

