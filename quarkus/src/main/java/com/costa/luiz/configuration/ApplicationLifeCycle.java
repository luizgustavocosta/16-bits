package com.costa.luiz.configuration;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationLifeCycle {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent event) {
        LOGGER.info("The application is starting...");
    }

    void onStop(@Observes ShutdownEvent event) {
        LOGGER.info("The application is stopping...");
    }

}
