import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DelegateSubscriber<T, R> implements Subscriber<T> {

    private Subscriber subscriber;

    public DelegateSubscriber(Subscriber<? super R> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(T t) {
        subscriber.onNext(t);
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }
}