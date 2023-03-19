/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s10;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jareco.Publishers;

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
        System.out.println("Publish on the single scheduler");
        Publishers.sixFruits() //
                .publishOn(Schedulers.single()) //
                .subscribe(x -> log.trace(x));

        System.out.println("Subscribe on the paraller scheduler");
        Publishers.sixFruits() //
                .subscribeOn(Schedulers.parallel()) //
                .subscribe(x -> log.trace(x));

        Thread.sleep(100);
        System.out.println("Done");
    }
}
