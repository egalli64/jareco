/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s08;

import java.util.Arrays;
import java.util.function.Consumer;

import com.example.jareco.Publishers;
import com.example.jareco.s03.Dog;

import reactor.core.publisher.Flux;

/**
 * Mapping on Flux and Mono
 */
public class Mapping {
    /**
     * A few examples on map(), flatMap(), flatMapMany()
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

        // Why flatMap?
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

        // flatMapMany()
        System.out.print("Flat-mapping to many, splitting a CSV string: ");
        Publishers.fruitsCsv() //
                .flatMapMany(csv -> Flux.fromArray(csv.split(","))) //
                .subscribe(print);
        System.out.println();
    }
}
