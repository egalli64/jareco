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
 * The log() and doOn...() / doFinally() methods
 */
public class SideEffects {
    private static final Logger log = LoggerFactory.getLogger(SideEffects.class);

    public static void main(String[] args) {
        log.trace("Log all the signals");
        Publishers.sixFruits().log().subscribe();

        log.trace("Add behavior on mono");
        Publishers.solution() //
                .doOnSubscribe(x -> log.trace("Subscription accepted")) //
                .doOnNext(x -> log.trace("Next is {}", x)) //
                .doOnSuccess(x -> log.trace("Successfully completed on {}", x)) //
                .doOnTerminate(() -> log.trace("Terminated")) //
                .doFinally(x -> log.trace("After terminating with {}", x)) //
                .subscribe();

        log.trace("Add behavior on flux");
        Publishers.sixFruits() //
                .doOnSubscribe(x -> log.trace("Subscription accepted")) //
                .doOnNext(x -> log.trace("Next is {}", x)) //
                .doOnTerminate(() -> log.trace("Terminated")) //
                .subscribe();

        log.trace("Add behavior on mono with error");
        Mono.error(new IllegalArgumentException("Crash")) //
                .doOnError(x -> log.trace("Error: {}", x.getMessage())) //
                .doFinally(x -> log.trace("After terminating with {}", x)) //
                .subscribe();
    }
}
