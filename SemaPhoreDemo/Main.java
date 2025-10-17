package SemaPhoreDemo;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final int capacity = 3;
        final Buffer buffer = new Buffer(capacity);

        // Two producers and two consumers for demonstration
        Thread p1 = new Thread(new Producer(buffer, 1, 10, 300), "Producer-1");
        Thread p2 = new Thread(new Producer(buffer, 100, 10, 500), "Producer-2");

        Thread c1 = new Thread(new Consumer(buffer, 10, 700), "Consumer-1");
        Thread c2 = new Thread(new Consumer(buffer, 10, 400), "Consumer-2");

        p1.start();
        p2.start();
        c1.start();
        c2.start();

        // Wait for threads to finish (optional)
        p1.join();
        p2.join();
        c1.join();
        c2.join();

        System.out.println("All producers and consumers finished.");
    }
}
