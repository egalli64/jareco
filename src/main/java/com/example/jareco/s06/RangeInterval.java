/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s06;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;

/**
 * Examples for Flux::range() and interval()  
 */
public class RangeInterval {
    private static final Logger log = LoggerFactory.getLogger(RangeInterval.class);

    public static void main(String[] args) throws InterruptedException {
        log.trace("A flux for a range");
        Flux.range(42, 3).subscribe(System.out::println);

        log.trace("A flux for an interval");
        Flux.interval(Duration.ofMillis(100)).subscribe(System.out::println);

        Thread.sleep(500);
    }
}
