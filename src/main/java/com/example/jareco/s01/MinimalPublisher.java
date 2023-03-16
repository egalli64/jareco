/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s01;

import java.util.concurrent.Flow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A minimal implementation for Flow.Publisher
 */
public class MinimalPublisher implements Flow.Publisher<Integer> {
    private static final Logger log = LoggerFactory.getLogger(MinimalPublisher.class);

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        log.trace("Enter");
        MinimalSubscription subscription = new MinimalSubscription(subscriber);
        System.out.println("Publisher has generated a subscription");
        subscriber.onSubscribe(subscription);
        log.trace("Exit");
    }
}