/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s11;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jareco.Publishers;

import reactor.core.publisher.Mono;

/**
 * Mono::block() and blockOptional()
 */
public class BlockOnMono {
    private static final Logger log = LoggerFactory.getLogger(BlockOnMono.class);

    /**
     * Examples
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        log.trace("Block on a 'good' mono");
        System.out.println("Result is " + Publishers.solution().log().block());

        log.trace("Optional block on a 'good' mono");
        Publishers.solution().log().blockOptional().ifPresent(x -> System.out.println("Result is " + x));

        log.trace("Block on a mono that completes without emitting a message");
        System.out.println("Result is " + Mono.empty().log().block());

        log.trace("Optional block on a mono that completes without emitting a message");
        // "orElse(Consumer)" would be a nice to have in a case like this
        boolean hasMessage = Mono.empty().log().blockOptional().isPresent();
        System.out.println(hasMessage ? "Unexpected" : "No result");

        log.trace("Block on a mono that completes with an error");
        try {
            Mono.error(new IllegalStateException("Crash")).log().block();
        } catch (IllegalStateException ex) {
            System.out.println("Error message is: " + ex.getMessage());
        }

        log.trace("Optional block on a mono that completes with an error");
        try {
            Mono.error(new IllegalStateException("Crash")).log().blockOptional();
        } catch (IllegalStateException ex) {
            System.out.println("Error message is: " + ex.getMessage());
        }

        log.trace("Timed block on a mono that never completes");
        try {
            Mono.never().log().block(Duration.ofMillis(100));
        } catch (IllegalStateException ex) {
            System.out.println("Error message is: " + ex.getMessage());
        }

        log.trace("Optional timed block on a mono that never completes");
        try {
            Mono.never().log().blockOptional(Duration.ofMillis(100));
        } catch (IllegalStateException ex) {
            System.out.println("Error message is: " + ex.getMessage());
        }
    }
}
