import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class IntPublisher<T> implements Publisher<T> {
    private Iterable<T> iterable;

    public IntPublisher(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    public void subscribe(Subscriber subscriber) {
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