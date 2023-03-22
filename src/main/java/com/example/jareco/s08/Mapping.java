/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s08;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

import com.example.jareco.Publishers;
import com.example.jareco.s03.Dog;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Mapping on Flux and Mono
 */
public class Mapping {
    /**
     * A few examples on map(), mapNotNull(), flatMap(), flatMapMany()
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        Consumer<String> print = x -> System.out.print(x + " ");

        // map()
        System.out.print("Map each dog to its name: ");
        Publishers.dogs().map(Dog::name).subscribe(print);
        System.out.println();

        System.out.print("Map each string to its uppercase version: ");
        Publishers.sixFruits().map(String::toUpperCase).subscribe(print);
        System.out.println();

        // Why mapNotNull()?
        System.out.println("Mapping using a function that could return null: ");
        Function<String, String> tasty = s -> s.length() > 5 ? s : null;

        // Mapper should not return null!
//        Publishers.sixFruits().map(tasty).filter(s -> s != null).subscribe(print);
        Publishers.sixFruits().mapNotNull(tasty).subscribe(print);
        System.out.println();

        // Why flatMap? /1
        System.out.print("From a mono with an id, map it to its dog name: ");
        Mono.just(6) //
                // maps an Integer to a Mono<Mono<Dog>>!
                .map(id -> Publishers.dogs().filter(dog -> dog.id() == id).next()) //
                .subscribe(x -> System.out.println(x.block()));

        System.out.print("Flat-mapping a Mono<Mono<Dog>>: ");
        Mono.just(6) //
                .flatMap(id -> Publishers.dogs().filter(dog -> dog.id() == id).next()) //
                .map(Dog::name) //
                .subscribe(System.out::println);

        // Why flatMap? /2
        System.out.println("I want the letters in the 'A' fruits: ");
        Publishers.sixFruits() //
                .filter(f -> f.startsWith("A")) //
                // map() gives a Flux of String[], not what I wanted!
                .map(f -> f.split("")) //
                .subscribe(x -> System.out.println(Arrays.toString(x)));

        // Raw use of flatMap()
        System.out.print("These are the letters in the 'A' fruits: ");
        Publishers.sixFruits() //
                .filter(f -> f.startsWith("A")) //
                // flatMap gives a Flux of String, good.
                .flatMap(f -> Flux.just(f.split(""))) //
                .subscribe(System.out::print);
        System.out.println();

        // Make the result a bit nicer
        System.out.print("Letters in the 'A' fruits (lowercase, no duplicate): ");
        Publishers.sixFruits() //
                .filter(f -> f.startsWith("A")) //
                .map(String::toLowerCase) //
                .flatMap(f -> Flux.just(f.split(""))) //
                .distinct() //
                .subscribe(System.out::print);
        System.out.println();

        // Flat mapping crossing fluxes
        System.out.print("Flat-mapping dogs with dice value as id: ");
        Publishers.diceValues() //
                .flatMap(x -> Publishers.dogs().filter(dog -> dog.id() == x))
                .subscribe(dog -> System.out.print(dog + " "));
        System.out.println();

        // flatMapMany() from Mono<String> to Flux<String>
        System.out.print("Flat-mapping to many, splitting a CSV string: ");
        Publishers.fruitsCsv() //
                .flatMapMany(csv -> Flux.fromArray(csv.split(","))) //
                .subscribe(print);
        System.out.println();

        // count()
        System.out.print("Count the items: ");
        Publishers.someDuplications().count().subscribe(System.out::println);

        // collectList()
        System.out.print("Put the items in a list: ");
        Publishers.someDuplications().collectList().subscribe(System.out::println);

        // buffer()
        System.out.print("Sum each sub-list of N values (the last one could have less elements): ");
        Publishers.someDuplications().buffer(3) //
                .map(ls -> ls.stream().mapToInt(Integer::intValue).sum()) //
                .subscribe(v -> System.out.print(v + " "));
        System.out.println();

        // zipWith() for tuple
        System.out.print("Merge two monos is a tuple-mono: ");
        Publishers.solution().zipWith(Mono.just("Hi")).subscribe(System.out::println);

        // zipWith() with function
        System.out.print("Add the values in two monos is a new mono: ");
        Publishers.solution().zipWith(Mono.just(12), (x, y) -> x + y).subscribe(System.out::println);
    }
}
