package com.test.subscription;

import com.test.Person;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;
import java.util.concurrent.Future;
import java.util.function.Consumer;

public class PersonSubscription<T> implements Flow.Subscription {
    private final Flow.Subscriber<T> subscriber;
    private final ExecutorService executor;
    private Future<?> future; // to allow cancellation       private boolean completed;
    private boolean completed;
    private Consumer<? super T> item;

    public PersonSubscription(Flow.Subscriber<T> subscriber, ExecutorService executor) {
        this.subscriber = subscriber;
        subscriber.onSubscribe(this);
        this.executor = executor;
    }

    @Override
    public synchronized void request(long n) {
        if (!completed) {
            completed = true;
            if (n <= 0) {
                IllegalArgumentException ex = new IllegalArgumentException();
                executor.execute(() -> subscriber.onError(ex));
            } else {
                future = executor.submit(() -> {
//                    subscriber.onNext(subscriber.);
                    subscriber.onComplete();
                });
            }
        }
    }

    @Override
    public synchronized void cancel() {
        completed = true;
        if (future != null) future.cancel(false);
    }
}
