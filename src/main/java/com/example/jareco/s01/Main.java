/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Smoke test for a minimal implementation of Flow
 */
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    /**
     * A minimal subscriber subscribes to a minimal publisher
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        log.trace("Enter");
        new MinimalPublisher().subscribe(new MinimalSubscriber());
        log.trace("Exit");
    }
}
