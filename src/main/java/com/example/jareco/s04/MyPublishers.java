/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s04;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Some publishers for examples
 */
public class MyPublishers {
    /**
     * Just an Integer
     * 
     * @return a Mono
     */
    public static Mono<Integer> solution() {
        return Mono.just(42);
    }

    /**
     * All the possible values in a dice
     * 
     * @return a Flux
     */
    public static Flux<Integer> diceValues() {
        return Flux.range(1, 6);
    }

    /**
     * All the possible values in a dice but 4
     * 
     * @return a Flux that emits also an error
     */
    public static Flux<Integer> diceValuesNoFour() {
        return Flux.range(1, 6).map(x -> {
            if (x == 4) {
                throw new IllegalStateException("Lost die " + x);
            } else {
                return x;
            }
        });
    }
}
