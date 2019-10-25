import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntPublisher implements Publisher<Integer> {
    Iterable<Integer> iterable = Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList());

    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscriber.onSubscribe(new Subscription() {
            @Override
            public void request(long l) {
                try {
                    iterable.forEach(subscriber::onNext);
                    subscriber.onComplete();
                }
                catch (Throwable t) {
                    subscriber.onError(t);
                }
            }

            @Override
            public void cancel() {

            }
        });
    }
}