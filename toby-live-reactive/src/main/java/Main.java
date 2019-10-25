public class Main {
    public static void main(String[] args) {
        IntPublisher intPublisher = new IntPublisher();
        IntSubscriber intSubscriber = new IntSubscriber();
        intPublisher.subscribe(intSubscriber);
    }
}