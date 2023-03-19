/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jareco.Publishers;

import reactor.core.publisher.Flux;

/**
 * Hot source
 */
public class TimedPublishing {
    private static final Logger log = LoggerFactory.getLogger(Publishing.class);

    /**
     * Two subscriptions on a hot source publisher
     * 
     * @param args not used
     * @throws InterruptedException if sleep is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        Flux<Long> flux = Publishers.tenHotTicks(100);

        flux.subscribe(x -> log.trace("F1: {}", x));
        Thread.sleep(600);

        flux.subscribe(x -> log.trace("F2: {}", x));
        Thread.sleep(600);
    }
}
