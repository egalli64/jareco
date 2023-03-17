/*
 * Introduction to Project Reactor Core
 * 
 * https://github.com/egalli64/jareco
 */
package com.example.jareco.s05;

import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jareco.s04.MyPublishers;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.SignalType;

/**
 * Extend the abstract class BaseSubscriber and override its hooks
 */
public class UsingSubscriber {
    private static final Logger log = LoggerFactory.getLogger(UsingSubscriber.class);

    /**
     * A subscriber with custom hooks
     */
    private static class MySubscriber extends BaseSubscriber<Integer> {
        private boolean cancelOnFour;

        /**
         * Constructor
         * 
         * @param cancelOnFour true if a cancel should be issued on 4
         */
        public MySubscriber(boolean cancelOnFour) {
            this.cancelOnFour = cancelOnFour;
        }

        /**
         * Constructor for plain use (no cancel issued)
         */
        public MySubscriber() {
            this(false);
        }

        @Override
        protected void hookOnSubscribe(Subscription subscription) {
            log.trace("Subscribed, requesting next");
            request(1);
        }

        @Override
        protected void hookOnNext(Integer value) {
            log.trace("Received {}: requesting next", value);
            if (cancelOnFour && value == 4) {
                cancel();
            } else {
                request(1);
            }
        }

        @Override
        protected void hookOnError(Throwable throwable) {
            log.trace(throwable.getMessage());
        }

        @Override
        protected void hookOnComplete() {
            log.trace("Successfully completed");
        }

        @Override
        protected void hookOnCancel() {
            log.trace("Canceling");
        }

        @Override
        protected void hookFinally(SignalType type) {
            log.trace("Done {}", type);
        }
    };

    /**
     * Show use of custom Subscriber in three different cases
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        log.trace("Enter");

        System.out.println("Dice values");
        MyPublishers.diceValues().subscribe(new MySubscriber());

        System.out.println("Dice values, requesting a cancel on 4");
        MyPublishers.diceValues().subscribe(new MySubscriber(true));

        System.out.println("Dice values with error on 4");
        MyPublishers.diceValuesNoFour().subscribe(new MySubscriber());
    }
}
