package Synchronization.AdderSubtractorWithSynchronization.Tasks;


import Synchronization.AdderSubtractorWithSynchronization.SharedResource.SharedResourceWithSynchronized;

public class Adder implements Runnable {
    SharedResourceWithSynchronized sharedResource;

    public Adder(SharedResourceWithSynchronized sharedResource) {
        this.sharedResource = sharedResource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            sharedResource.add();
        }

    }

}
