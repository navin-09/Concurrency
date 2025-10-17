package Synchronization.AdderSubtrctor.tasks;

import Synchronization.AdderSubtrctor.SharedResource.SharedResource;

public class Adder implements Runnable{  
    SharedResource sharedResource;

    public Adder(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }

    public void run(){
        for (int i =0; i< 1000;i++){
            sharedResource.add();
        }

    }

}
