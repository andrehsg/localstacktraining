package com.daitan;

import com.daitan.core.SqsService;
import com.daitan.resources.LocalStackResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.java.Log;

import java.net.URISyntaxException;

@Log
public class localstackApplication extends Application<localstackConfiguration> {

    public static void main(final String[] args) throws Exception {
        new localstackApplication().run(args);
    }

    @Override
    public String getName() {
        return "localstack";
    }

    @Override
    public void initialize(final Bootstrap<localstackConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final localstackConfiguration configuration,
                    final Environment environment) throws URISyntaxException {

        final LocalStackResource resource = new LocalStackResource();
        environment.jersey().register(resource);


    }

}
