package com.test.subscriber;

import com.test.Person;
import com.test.generic.SampleSubscriber;

import java.util.function.Consumer;

public class ChangePersonNameSubscriber extends SampleSubscriber<Person> {
    public ChangePersonNameSubscriber(long bufferSize, Consumer<Person> consumer) {
        super(bufferSize, consumer);
    }
}
