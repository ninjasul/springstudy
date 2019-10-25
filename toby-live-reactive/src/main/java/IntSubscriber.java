import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class IntSubscriber implements Subscriber<Integer> {
    public void onSubscribe(Subscription subscription) { 
        log.debug("{} - onSubscribe", Thread.currentThread().getName());
        subscription.request(Long.MAX_VALUE);
    }

    public void onNext(Integer i) {
        log.debug("{} - onNext: {}", Thread.currentThread().getName(), i);
    }

    public void onError(Throwable throwable) {
        log.debug("{} - onError: {}", Thread.currentThread().getName(), throwable.getMessage());
    }

    public void onComplete() {
        log.debug("{} - onComplete", Thread.currentThread().getName());
    }
}