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
 * The onErrorResume() and onErrorContinue() methods
 */
public class ErrorHandling {
    private static final Logger log = LoggerFactory.getLogger(ErrorHandling.class);

    public static void main(String[] args) {
        log.trace("Error resume");
        Mono.error(new IllegalArgumentException("Crash")) //
                .onErrorResume(ex -> {
                    log.warn("Resuming from error: {}", ex.getMessage());
                    return Publishers.solution();
                }) //
                .subscribe(System.out::println);

        log.trace("Error continue");
        Publishers.diceValuesNoFour() //
                .onErrorContinue((ex, i) -> {
                    log.warn("Continuing after error: {}", ex.getMessage());
                }) //
                .subscribe(System.out::println);
    }
}
