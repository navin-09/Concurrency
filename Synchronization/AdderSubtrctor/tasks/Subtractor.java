package Synchronization.AdderSubtrctor.tasks;

import Synchronization.AdderSubtrctor.SharedResource.SharedResource;

public class Subtractor implements Runnable{
    public SharedResource sharedResource;

    public Subtractor(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    public void run(){
        for (int i=0;i< 1000;i++){
            sharedResource.subtract();
        }
    }
    
}
