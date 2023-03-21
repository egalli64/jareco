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
    private static final Dog[] dogs = { new Dog(42, "Tom"), new Dog(12, "Bob"), new Dog(18, "Kim") };

    public static void main(String[] args) {
        // Flux::just() creates a Flux that emits the items and then completes
        log.trace("A Flux<Dog> just for Tom and Kim");
        Flux.just(dogs[0], dogs[2]).subscribe(System.out::println);

        // Mono::just() creates a Mono that emits the item and then completes
        log.trace("A Mono<Dog> just for Tom");
        Mono.just(dogs[0]).subscribe(System.out::println);

        // Flux::empty() creates a Flux that completes without emitting any item
        log.trace("An empty Flux<Object>");
        Flux.empty().subscribe(System.out::println);

        // Flux::empty() creates a Mono that completes without emitting any item
        log.trace("An empty Mono<Object>");
        Mono.empty().subscribe(System.out::println);

        // Mono::fromCallable() a supplier generate the item at runtime
        log.trace("A Mono<Dog> for Bob from callable");
        Mono<Dog> callDog = Mono.fromCallable(() -> dogs[1]);
        callDog.subscribe(System.out::println);

        // Flux::fromArray() an array is used as source
        log.trace("A Flux<Dog> based on an array");
        Flux.fromArray(dogs).subscribe(System.out::println);

        // Flux::fromIterable() an iterable is used as source
        log.trace("A Flux<Dog> based on an iterable");
        List<Dog> liDogs = Arrays.asList(dogs);
        Flux.fromIterable(liDogs).subscribe(System.out::println);

        // Flux::fromStream() a stream is used as source
        log.trace("A Flux<Dog> based on a stream");
        Stream<Dog> stDogs = liDogs.stream();
        Flux.fromStream(stDogs).subscribe(System.out::println);

        log.trace("A Flux<Dog> based on a Publisher");
        Flux.from(callDog).subscribe(System.out::println);
    }
}
