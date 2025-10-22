package Synchronization.AdderSubtractor;

import Synchronization.AdderSubtractor.SharedResource.SharedResource;
import Synchronization.AdderSubtractor.tasks.Adder;
import Synchronization.AdderSubtractor.tasks.Subtractor;

public class Main {

    public static void main(String[] args) {

        SharedResource sharedResource = new SharedResource();
        Adder adder = new Adder(sharedResource);
        Subtractor subtractor = new Subtractor(sharedResource);

        Thread thread1 = new Thread(adder);
        Thread thread2 = new Thread(subtractor);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.out.println("Final value: " + sharedResource.getCount());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
