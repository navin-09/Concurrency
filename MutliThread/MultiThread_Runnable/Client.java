package MutliThread.MultiThread_Runnable;

public class Client {
    public void startTasks() {
        Runnable task1 = new NumberPrinterTask(1, 50);
        Runnable task2 = new NumberPrinterTask(51, 100);
        
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
