package MutliThread.MultiThread_ThreadPool;

public class NumberPrinterTask implements Runnable {
    private final int start;
    private final int end;

    public NumberPrinterTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            System.out.println(Thread.currentThread().getName() + " -> " + i);
            try {
                Thread.sleep(10); // small delay to see interleaving
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
}
