/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s07;

import java.util.function.Consumer;

import com.example.jareco.s04.MyPublishers;

/**
 * Some operator to filter a Flux
 */
public class Filtering {
    public static void main(String[] args) {
        Consumer<Integer> print = x -> System.out.print(x + " ");

        System.out.print("All values: ");
        MyPublishers.someDuplications().subscribe(print);
        System.out.println();

        // Filtering
        System.out.print("Filter even: ");
        MyPublishers.someDuplications().filter(x -> x % 2 == 0).subscribe(print);
        System.out.println();

        System.out.print("Filter distinct: ");
        MyPublishers.someDuplications().distinct().subscribe(print);
        System.out.println();

        System.out.print("Filter distinct until changed: ");
        MyPublishers.someDuplications().distinctUntilChanged().subscribe(print);
        System.out.println();

        System.out.print("Ignore elements (nothing pass the filter): ");
        MyPublishers.someDuplications().ignoreElements().subscribe(print);
        System.out.println();

        System.out.print("Filter just the element in position 4: ");
        MyPublishers.someDuplications().elementAt(4).subscribe(print);
        System.out.println();

        // Take
        System.out.print("Take first three: ");
        MyPublishers.someDuplications().take(3).subscribe(print);
        System.out.println();

        System.out.print("Take last three - assuming bounded stream: ");
        MyPublishers.someDuplications().takeLast(3).subscribe(print);
        System.out.println();

        System.out.print("Take last: ");
        MyPublishers.someDuplications().last().subscribe(print);
        System.out.println();

        // Skip
        System.out.print("Skip first three: ");
        MyPublishers.someDuplications().skip(3).subscribe(print);
        System.out.println();

        System.out.print("Skip last three - assuming bounded stream: ");
        MyPublishers.someDuplications().skipLast(3).subscribe(print);
        System.out.println();

        System.out.print("Skip until 4: ");
        MyPublishers.someDuplications().skipUntil(x -> x == 4).subscribe(print);
        System.out.println();

        // Extra
        System.out.print("Enforce single: ");
        MyPublishers.aFruitFlux().single().subscribe(System.out::println);
    }
}
