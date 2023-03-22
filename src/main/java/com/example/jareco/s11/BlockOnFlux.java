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

import reactor.core.publisher.Flux;

/**
 * Flux::blockFirst(), blockLast()
 */
public class BlockOnFlux {
    private static final Logger log = LoggerFactory.getLogger(BlockOnFlux.class);

    /**
     * Examples
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        log.trace("The flux");
        System.out.println(Publishers.sixFruits().collectList().block());

        log.trace("Block on the first flux message");
        System.out.println(Publishers.sixFruits().log().blockFirst());

        log.trace("Block on the last flux message");
        System.out.println(Publishers.sixFruits().log().blockLast());

        log.trace("Block on the first flux message - empty");
        System.out.println(Flux.empty().log().blockFirst());

//        log.trace("Block on the first flux message - never, hangs forever");
//        System.out.println(Flux.never().log().blockFirst());

        log.trace("Timed block on the first flux message - never");
        try {
            System.out.println(Flux.never().log().blockFirst(Duration.ofMillis(100)));
        } catch (IllegalStateException ex) {
            System.out.println("Error message is: " + ex.getMessage());
        }
    }
}
