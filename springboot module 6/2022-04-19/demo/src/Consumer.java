import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class Consumer implements Flow.Subscriber<Student> {

    private Flow.Subscription subscription;
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        System.out.println("Subscribed");
        this.subscription = subscription;
        subscription.request(2);
    }

    @Override
    public void onNext(Student item) {
        System.out.println("Received: " + item.toString());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }

    public static void main(String[] args) {
        Consumer consumer = new Consumer();
        SubmissionPublisher<Student> publisher = new SubmissionPublisher<>();
        publisher.subscribe(consumer);

        for(int i = 0; i < 10; i++) {
            publisher.submit(new Student(i, "Student " + i));
        }
        publisher.close();
    }
}
