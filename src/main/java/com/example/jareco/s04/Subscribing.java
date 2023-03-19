/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jareco.Publishers;

import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Introducing subscribe() on Flux and Mono
 */
public class Subscribing {
    private static final Logger log = LoggerFactory.getLogger(Subscribing.class);

    /**
     * Using a few subscribe() overloads
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        log.trace("Enter");

        /*
         * Get a Mono, subscribe to it, and then cancel the subscription
         * 
         * No feedback, since no one is actually listening to the signals produced by mono
         */
        Mono<Integer> m0 = Publishers.solution();
        log.trace("Subscribe to m0 - no consumer");
        Disposable dmi = m0.subscribe();
        log.trace("Useless dispose to m0");
        dmi.dispose();

        // Get a Mono, subscribe to it setting a consumer for its message
        Mono<Integer> m1 = Publishers.solution();
        log.trace("Subscribe to m1 - print the solution");
        m1.subscribe(System.out::println);

        // Get a Flux, subscribe to it setting a consumer for its messages
        Flux<Integer> f1 = Publishers.diceValues();
        log.trace("Subscribe to f1 - print the dice values");
        f1.subscribe(System.out::println);

        // Get a Flux, subscribe to it setting a consumer for its messages and errors
        Flux<Integer> f2 = Publishers.diceValuesNoFour();
        log.trace("Subscribe to f2 - print the dice values and handle errors");
        f2.subscribe(System.out::println, System.out::println);

        // Get a Flux, subscribe to it setting a runnable for completion
        Flux<Integer> f3 = Publishers.diceValues();
        log.trace("Subscribe to f3 - only print a message on completion");
        f3.subscribe(null, null, () -> System.out.println("Done"));

        log.trace("Exit");
    }
}
