package SemaPhoreDemo;


public class Consumer implements Runnable {
    private final Buffer buffer;
    private final int consumeCount;
    private final long delayMs;

    public Consumer(Buffer buffer, int consumeCount, long delayMs) {
        this.buffer = buffer;
        this.consumeCount = consumeCount;
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < consumeCount; i++) {
                int item = buffer.take();
                // do something with item (here we just sleep to simulate work)
                if (delayMs > 0) Thread.sleep(delayMs);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
