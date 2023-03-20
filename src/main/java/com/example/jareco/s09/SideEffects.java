/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s09;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jareco.Publishers;

import reactor.core.publisher.Mono;

/**
 * The log() and doOn...() methods
 */
public class SideEffects {
    private static final Logger log = LoggerFactory.getLogger(SideEffects.class);

    public static void main(String[] args) {
        log.trace("Log all the signals");
        Publishers.sixFruits().log().subscribe();

        log.trace("Add behavior on mono subscribe / success / termination");
        Publishers.solution() //
                .doOnSubscribe(x -> log.trace("Subscription accepted")) //
                .doOnSuccess(x -> log.trace("Successfully completed on {}", x)) //
                .doOnTerminate(() -> log.trace("Terminated")) //
                .subscribe();

        log.trace("Add behavior on flux subscribe / termination");
        Publishers.sixFruits() //
                .doOnSubscribe(x -> log.trace("Subscription accepted")) //
                .doOnTerminate(() -> log.trace("Terminated")) //
                .subscribe();

        log.trace("Add behavior on error");
        Mono.error(new IllegalArgumentException("Crash")) //
                .doOnError(x -> log.trace("Error: {}", x.getMessage())) //
                .subscribe();
    }
}
