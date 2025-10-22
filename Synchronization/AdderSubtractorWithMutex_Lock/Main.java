package Synchronization.AdderSubtractorWithMutex_Lock;

import Synchronization.AdderSubtractorWithMutex_Lock.SharedResource.SharedResourceWithLock;
import Synchronization.AdderSubtractorWithMutex_Lock.Tasks.Adder;
import Synchronization.AdderSubtractorWithMutex_Lock.Tasks.Subtractor;

public class Main {
    public static void main(String[] args) {

        SharedResourceWithLock SharedResource = new SharedResourceWithLock();
        Adder adder = new Adder(SharedResource);
        Subtractor subtractor = new Subtractor(SharedResource);
        Thread thread1 = new Thread(adder);
        Thread thread2 = new Thread(subtractor);
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.out.println("Final value: " + SharedResource.getCount());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
