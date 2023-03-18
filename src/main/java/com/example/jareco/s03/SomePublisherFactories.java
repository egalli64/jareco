/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s03;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * A few common factory method to create a Flux or a Mono
 */
public class SomePublisherFactories {
    private static final Logger log = LoggerFactory.getLogger(SomePublisherFactories.class);

    // Source of dogs for the publishers below
    private static final Dog[] arDogs = { new Dog(42, "Tom"), new Dog(12, "Bob"), new Dog(18, "Kim") };

    public static void main(String[] args) {
        log.trace("Enter");

        // Flux::just() creates a Flux that emits the items and then completes
        Flux<Dog> f1 = Flux.just(arDogs[0], arDogs[2]);

        log.trace("A flux just for Tom and Kim");
        f1.subscribe(System.out::println);

        // Mono::just() creates a Mono that emits the item and then completes
        Mono<Dog> m1 = Mono.just(arDogs[0]);

        log.trace("A mono just for Tom");
        m1.subscribe(System.out::println);

        // Flux::empty() creates a Flux that completes without emitting any item
        Flux<Dog> f2 = Flux.empty();

        log.trace("An empty flux");
        f2.subscribe(System.out::println);

        // Flux::empty() creates a Mono that completes without emitting any item
        Mono<Dog> m2 = Mono.empty();

        log.trace("An empty mono");
        m2.subscribe(System.out::println);

        // Mono::fromCallable() a supplier generate the item at runtime
        Mono<Dog> m3 = Mono.fromCallable(() -> arDogs[1]);

        log.trace("A mono for Bob from callable");
        m3.subscribe(System.out::println);

        // Flux::fromArray() an array is used as source
        Flux<Dog> f3 = Flux.fromArray(arDogs);

        log.trace("A flux for all dogs in an array");
        f3.subscribe(System.out::println);

        // Flux::fromIterable() an iterable is used as source
        List<Dog> liDogs = Arrays.asList(arDogs);
        Flux<Dog> f4 = Flux.fromIterable(liDogs);

        log.trace("A flux for all dogs in an iterable");
        f4.subscribe(System.out::println);

        // Flux::fromStream() a stream is used as source
        Stream<Dog> stDogs = liDogs.stream();
        Flux<Dog> f5 = Flux.fromStream(stDogs);

        log.trace("A flux for all dogs in a stream");
        f5.subscribe(System.out::println);

        // Flux::range() from start, count integers are generated
        Flux<Integer> f6 = Flux.range(42, 3);

        log.trace("A flux for a range");
        f6.subscribe(System.out::println);

        log.trace("Exit");
    }
}
