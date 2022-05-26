package com.test.subscriber;

import com.test.Person;
import com.test.generic.SampleSubscriber;

import java.util.function.Consumer;


public class ChangePersonStatusSubscriber extends SampleSubscriber<Person> {
    public ChangePersonStatusSubscriber(long bufferSize, Consumer<Person> consumer) {
        super(bufferSize, consumer);
    }
}
