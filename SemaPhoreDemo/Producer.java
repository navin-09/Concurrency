package SemaPhoreDemo;

public class Producer implements Runnable {
    private final Buffer buffer;
    private final int startValue;
    private final int produceCount;
    private final long delayMs; // optional delay to slow output

    public Producer(Buffer buffer, int startValue, int produceCount, long delayMs) {
        this.buffer = buffer;
        this.startValue = startValue;
        this.produceCount = produceCount;
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        int v = startValue;
        try {
            for (int i = 0; i < produceCount; i++) {
                buffer.put(v++);
                if (delayMs > 0) Thread.sleep(delayMs);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
