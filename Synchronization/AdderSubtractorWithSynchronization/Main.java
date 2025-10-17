package Synchronization.AdderSubtractorWithSynchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




import Synchronization.AdderSubtractorWithSynchronization.SharedResource.SharedResourceWithSynchronized;
import Synchronization.AdderSubtractorWithSynchronization.Tasks.Adder;
import Synchronization.AdderSubtractorWithSynchronization.Tasks.Subtractor;

public class Main {

    public static void main(String[] args) {
        SharedResourceWithSynchronized sharedResource = new SharedResourceWithSynchronized();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Adder adder = new Adder(sharedResource);
        Subtractor subtractor = new Subtractor(sharedResource);
        executor.submit(adder);
        executor.submit(subtractor);
        executor.shutdown();
        System.out.println("Final value: " + sharedResource.getCount());

    
        
    }
    
}
