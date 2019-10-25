import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        IntPublisher intPublisher = new IntPublisher(Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList()));
        Publisher<String> mapPublisher = mapPub(intPublisher, s -> "[" + s + "]");
        //Publisher<Integer> map2Publisher = mapPub(mapPublisher, s -> -s);
        //Publisher<Integer> sumPublisher = sumPub(map2Publisher);
        Publisher<String> reducePublisher = reducePub(mapPublisher, "", (a, b) -> a + "-" + b);
        reducePublisher.subscribe(new LogSubscriber());
    }

    private static <T, R> Publisher<R> reducePub(Publisher<T> publisher, R init, BiFunction<R, T, R> bf) {
        return subscriber -> publisher.subscribe(new DelegateSubscriber<T, R>(subscriber) {
            R result = init;

            @Override
            public void onNext(T t) {
                result = bf.apply(result, t);
                subscriber.onNext(result);
            }

            @Override
            public void onComplete() {
                subscriber.onComplete();
            }
        });
    }

    private static <T, R> Publisher<R> mapPub(Publisher<T> publisher, Function<T, R> f) {
        return new Publisher<R>() {
            @Override
            public void subscribe(Subscriber<? super R> subscriber) {
                publisher.subscribe(new DelegateSubscriber<T, R>(subscriber) {
                    @Override
                    public void onNext(T t) {
                        subscriber.onNext(f.apply(t));
                    }
                });
            }
        };
    }

    /*private static <T> Publisher<T> sumPub(Publisher<T> publisher) {
        return subscriber -> publisher.subscribe(new DelegateSubscriber<T, T>(subscriber) {
            T sum;

            @Override
            public void onNext(T t) {
                sum += t;
            }

            @Override
            public void onComplete() {
                subscriber.onNext(sum);
                subscriber.onComplete();
            }
        });
    }*/
}