/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s06;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

/**
 * Flux::generate()
 * 
 * Synchronous one-by-one. Generation is based on a state.
 */
public class Generate {
    private static final Logger log = LoggerFactory.getLogger(Generate.class);

    /**
     * Two examples for Flux::generate()
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        log.trace("Enter");

        // A flux generated programmatically
        Flux<Integer> multiplesOfThree = Flux.generate(() -> 0, (state, sink) -> {
            sink.next(state * 3);
            if (state == 10) {
                sink.complete();
            }
            return state + 1;
        });

        log.trace("Multiples of three");
        multiplesOfThree.subscribe(System.out::println);

        // The fibonacci sequence - when overflow is detected, the generation stops
        Flux<Long> fibonacci = Flux.generate(() -> Tuples.of(0L, 1L), (state, sink) -> {
            long cur = state.getT1() + state.getT2();
            if (cur < 0) {
                sink.complete();
            }
            sink.next(cur);
            return Tuples.of(state.getT2(), cur);
        });

        log.trace("Fibonacci");
        fibonacci.subscribe(System.out::println);

        log.trace("Exit");
    }
}
