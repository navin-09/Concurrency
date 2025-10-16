package MultiThread_Callable;

import java.util.concurrent.Callable;

public class SumTask implements Callable<Integer> {

    private final int start;
    private final int end;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
            Thread.sleep(5); // just to simulate computation delay
        }
        System.out.println(Thread.currentThread().getName() +
                " calculated sum from " + start + " to " + end + " = " + sum);
        return sum;
    }
}