/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s03;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A few common factory method to create a Flux or a Mono
 */
public class SomePublisherFactories {
    private static final Logger log = LoggerFactory.getLogger(SomePublisherFactories.class);

    // Used as source for the publishers in this example
    private static List<Dog> dogs = List.of(new Dog(42, "Tom"), new Dog(12, "Bob"), new Dog(18, "Kim"));

    public static void main(String[] args) {
        log.trace("Enter");

        // Flux::just() creates a Flux that emits the items and then completes
        Flux<Dog> f1 = Flux.just(dogs.get(0), dogs.get(2));

        log.trace("Subscribing/consuming a flux just for Tom and Kim");
        f1.subscribe(System.out::println);

        // Mono::just() creates a Mono that emits the item and then completes
        Mono<Dog> m1 = Mono.just(dogs.get(0));

        log.trace("Subscribing/consuming a mono just for Tom");
        m1.subscribe(System.out::println);

        // Flux::empty() creates a Flux that completes without emitting any item
        Flux<Dog> f2 = Flux.empty();

        log.trace("Subscribing/consuming an empty flux");
        f2.subscribe(System.out::println);

        // Flux::empty() creates a Mono that completes without emitting any item
        Mono<Dog> m2 = Mono.empty();

        log.trace("Subscribing/consuming an empty mono");
        m2.subscribe(System.out::println);

        // Flux::fromIterable() an iterable is used as source
        Flux<Dog> f3 = Flux.fromIterable(dogs);

        log.trace("Subscribing/consuming a flux for all dogs");
        f3.subscribe(System.out::println);

        // Flux::range() from start, count integers are generated
        Flux<Integer> f4 = Flux.range(42, 3);

        log.trace("Subscribing/consuming a flux for a range");
        f4.subscribe(System.out::println);

        // Mono::fromCallable() a supplier generate the item at runtime
        Mono<Dog> m3 = Mono.fromCallable(() -> dogs.get(1));

        log.trace("Subscribing/consuming a mono for Bob from callable");
        m3.subscribe(System.out::println);

        log.trace("Exit");
    }
}
