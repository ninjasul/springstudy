import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class LogSubscriber<T> implements Subscriber<T> {
    public void onSubscribe(Subscription subscription) { 
        log.debug("{} - onSubscribe", Thread.currentThread().getName());
        subscription.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(T t) {
        log.debug("{} - onNext: {}", Thread.currentThread().getName(), t);
    }

    public void onError(Throwable throwable) {
        log.debug("{} - onError: {}", Thread.currentThread().getName(), throwable.getMessage());
    }

    public void onComplete() {
        log.debug("{} - onComplete", Thread.currentThread().getName());
    }
}