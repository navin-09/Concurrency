package MutliThread.MultiThread_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {

    public void startTasks() {
        // 1️⃣ Create a thread pool with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 2️⃣ Submit multiple NumberPrinterTasks to the pool
        executor.submit(new NumberPrinterTask(1, 25));
        executor.submit(new NumberPrinterTask(26, 50));
        executor.submit(new NumberPrinterTask(51, 75));
        executor.submit(new NumberPrinterTask(76, 100));

        // 3️⃣ Shutdown pool (gracefully)
        executor.shutdown();

        System.out.println("✅ All tasks submitted to thread pool!");
    }
}