package com.test.publisher;

import com.test.Person;
import com.test.subscription.PersonSubscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.ForkJoinPool;

public class PersonPublisher implements Flow.Publisher<Person> {
    private final ExecutorService executor = ForkJoinPool.commonPool(); // daemon-based
    private boolean subscribed; // true after first subscribe

    @Override
    public synchronized void subscribe(Flow.Subscriber<? super Person> subscriber) {
//        if (subscribed)
//            subscriber.onError(new IllegalStateException()); // only one allowed
//        else {
//            subscribed = true;
//            subscriber.onSubscribe(new PersonSubscription(subscriber, executor));
//        }

        subscribed = true;
        subscriber.onSubscribe(new PersonSubscription(subscriber, executor));
    }
}
