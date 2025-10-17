package Synchronization.AdderSubtractorWithSynchronization.Tasks;

import Synchronization.AdderSubtractorWithSynchronization.SharedResource.SharedResourceWithSynchronized;

public class Subtractor implements Runnable {
    SharedResourceWithSynchronized sharedResource;

    public Subtractor(SharedResourceWithSynchronized sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override 
    public void run() {
        for (int i = 0; i < 1000; i++) {
            sharedResource.subtract();
        }
    }
    
}
