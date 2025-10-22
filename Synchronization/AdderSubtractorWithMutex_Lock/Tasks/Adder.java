package Synchronization.AdderSubtractorWithMutex_Lock.Tasks;

import Synchronization.AdderSubtractorWithMutex_Lock.SharedResource.SharedResourceWithLock;


public class Adder implements Runnable {
    SharedResourceWithLock sharedResource;

    public Adder(SharedResourceWithLock sharedResource) {
        this.sharedResource = sharedResource;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            sharedResource.add();
        }
    }

}
