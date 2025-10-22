package Synchronization.AdderSubtractorAtomic;

public class Subtractor implements Runnable {
    private SharedData data;

    public Subtractor(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            data.subtract();
        }
    }
}
