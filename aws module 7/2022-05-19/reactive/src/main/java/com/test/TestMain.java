package com.test;

import com.test.publisher.PersonPublisher;
import com.test.subscriber.ChangePersonStatusSubscriber;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.Flow;

public class TestMain {
    public static void main(String[] args) {
        ChangePersonStatusSubscriber personSubscriber = new ChangePersonStatusSubscriber(Flow.defaultBufferSize(), (person) -> person.toStatus());
        PersonPublisher personPublisher = new PersonPublisher();
//        personPublisher.subscribe(personSubscriber);
        SubmissionPublisher<Person> submissionPublisher = new SubmissionPublisher<>();

    }
}
