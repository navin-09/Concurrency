package MultiThread_Print100_Numbers;

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
            System.out.println(i);
        }
    }
    
}
