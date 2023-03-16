/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s01;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Minimal _synchronous_ implementation for Flow.Subscriber
 */
public class MinimalSubscriber implements Flow.Subscriber<Integer> {
    private static final Logger log = LoggerFactory.getLogger(MinimalSubscriber.class);

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        log.trace("Enter");
        this.subscription = subscription;
        System.out.println("Subscriber received its subscription and request a message");
        subscription.request(1);
        log.trace("Exit");
    }

    /**
     * Once this minimal subscriber receives zero, its job is done
     */
    @Override
    public void onNext(Integer item) {
        log.trace("Enter {}", item);
        if (item != 0) {
            System.out.println("Subscriber received " + item);
            subscription.request(1);
        } else {
            subscription.cancel();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void onComplete() {
        log.info("Subscription done");
    }
}
