/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco;

import java.time.Duration;

import com.example.jareco.s03.Dog;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Some publishers for examples
 */
public class Publishers {
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
                throw new IllegalStateException("Lost dice " + x);
            } else {
                return x;
            }
        });
    }

    /**
     * A few values, some of them duplicated
     * 
     * @return a Flux
     */
    public static Flux<Integer> someDuplications() {
        return Flux.just(3, 5, 2, 2, 3, 4, 6, 2);
    }

    /**
     * A single value in a flux
     * 
     * @return a Flux
     */
    public static Flux<String> aFruitFlux() {
        return Flux.just("Banana");
    }

    /**
     * A flux of dogs
     * 
     * @return a Flux
     */
    public static Flux<Dog> dogs() {
        return Flux.just(new Dog(1, "Bob"), new Dog(6, "Kim"), new Dog(9, "Jim"));
    }

    /**
     * A flux of strings
     * 
     * @return a Flux
     */
    public static Flux<String> sixFruits() {
        return Flux.just("Lemon", "Peach", "Raspberry", "Ananas", "Apple", "Strawberry");
    }

    /**
     * A CSV string as a mono
     * 
     * @return a Mono
     */
    public static Mono<String> fruitsCsv() {
        return Mono.just("Lemon,Peach,Raspberry,Ananas,Apple,Strawberry");
    }

    /**
     * A shared flux of ten ticks, each every delta ms
     * 
     * @param delta gap between generation of each tick
     * @return a shared flux
     */
    public static Flux<Long> tenHotTicks(long delta) {
        return Flux.interval(Duration.ofMillis(delta)).take(10).share();
    }
}
