/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jareco.Publishers;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * Changing scheduler
 */
public class Scheduling {
    private static final Logger log = LoggerFactory.getLogger(Scheduling.class);

    /**
     * Examples for publishOn() and subscribeOn()
     * 
     * @param args not used
     * @throws InterruptedException if sleep is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Subscribe on main, publish on single");
        Publishers.sixFruits() //
                .publishOn(Schedulers.single()) //
                .log() //
                .subscribe(x -> log.trace(x));
        Thread.sleep(100);

        System.out.println("Subscribe on single");
        Publishers.sixFruits() //
                .log() //
                .subscribeOn(Schedulers.single()) //
                .subscribe(x -> log.trace(x));
        Thread.sleep(100);

        System.out.println("Subscribe on single, publish on parallel");
        Publishers.sixFruits() //
                .publishOn(Schedulers.parallel()) //
                .log() //
                .subscribeOn(Schedulers.single()) //
                .subscribe(x -> log.trace(x));
        Thread.sleep(100);

        System.out.println("Subscribe on single, publish on parallel, then switch to bounded elastic");
        Publishers.sixFruits() //
                .publishOn(Schedulers.parallel()) //
                .log() //
                .publishOn(Schedulers.boundedElastic()) //
                .map(String::length).subscribeOn(Schedulers.single()) //
                .subscribe(x -> log.trace("Len: {}", x));
        Thread.sleep(100);

        System.out.println("Subscribe on main, flatMap on parallel");
        Publishers.sixFruits() //
                .log() //
                .map(String::toLowerCase) //
                .flatMap(f -> Flux.just(f.split("")).subscribeOn(Schedulers.parallel())) //
                .distinct() //
                .subscribe(System.out::println);
        Thread.sleep(100);

        System.out.println("Done");
    }
}
