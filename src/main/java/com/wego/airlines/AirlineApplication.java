package com.wego.airlines;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class AirlineApplication extends Application<Configuration> {

    public static void main(String args[]) throws Exception {
        new AirlineApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<Configuration> bootstrap) {

    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        environment.jersey().register(AirlineResource.class);
    }
}
