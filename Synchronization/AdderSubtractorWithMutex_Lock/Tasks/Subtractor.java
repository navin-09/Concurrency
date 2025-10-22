package Synchronization.AdderSubtractorWithMutex_Lock.Tasks;

import Synchronization.AdderSubtractorWithMutex_Lock.SharedResource.SharedResourceWithLock;

public class Subtractor implements Runnable {

    SharedResourceWithLock sharedResource;

    public Subtractor(SharedResourceWithLock sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            sharedResource.subtractor();
        }
    }

}
