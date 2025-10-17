package MutliThread.MultiThread_Callable;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {

    public void startTasks() {
        // 1️⃣ Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 2️⃣ Create multiple Callable tasks
        List<Callable<Integer>> tasks = List.of(
                new SumTask(1, 25),
                new SumTask(26, 50),
                new SumTask(51, 75),
                new SumTask(76, 100)
        );

        try {
            // 3️⃣ Submit all tasks and collect their Futures
            List<Future<Integer>> results = executor.invokeAll(tasks);

            // 4️⃣ Aggregate results
            int totalSum = 0;
            for (Future<Integer> f : results) {
                totalSum += f.get(); // waits for each task to finish
            }

            System.out.println("\n✅ Total sum of numbers 1–100 = " + totalSum);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // always shut down
        }
    }
    
}
