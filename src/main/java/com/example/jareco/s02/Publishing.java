/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * First approach to Publisher in Project Reactor
 */
public class Publishing {
    private static final Logger log = LoggerFactory.getLogger(Publishing.class);

    /**
     * Flux and Mono
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        log.trace("Enter");

        // Flux::just() creates a Flux that emits the provided elements and then completes
        Flux<String> jf = Flux.just("foo", "bar", "foobar");

        log.trace("Subscribing to the flux and consuming it");
        jf.subscribe(System.out::println);

        // Mono::just() creates a Mono that emits the specified item, captured at instantiation time
        Mono<String> jm = Mono.just("Hello");

        log.trace("Subscribing to the mono and consuming it");
        jm.subscribe(System.out::println);

        // Mono::never() return a Mono that will never signal anything, essentially running indefinitely
        Mono<String> nm = Mono.never();

        log.trace("Subscribing to a 'never' mono and consuming it won't lead to anything");
        nm.subscribe(System.out::println);

        log.trace("Exit");
    }
}
