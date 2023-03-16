/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s01;

import java.util.Random;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A minimal _synchronous_ implementation for Flow.Subscription
 */
public class MinimalSubscription implements Subscription {
    private static final Logger log = LoggerFactory.getLogger(MinimalSubscription.class);

    private static final int MAX_VALUE = 2;
    private Random random = new Random();

    private Subscriber<? super Integer> subscriber;

    /**
     * Constructor
     * 
     * @param subscriber a subscriber
     */
    public MinimalSubscription(Subscriber<? super Integer> subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Generate a random integer in [0..MAX_VALUE], the parameter n is ignored
     */
    @Override
    public void request(long n) {
        int message = random.nextInt(MAX_VALUE + 1);
        log.info("Subscription generates {}", message);
        subscriber.onNext(message);
    }

    @Override
    public void cancel() {
        log.info("Subscription canceled");
        subscriber.onComplete();
    }
}