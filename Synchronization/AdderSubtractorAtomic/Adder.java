package Synchronization.AdderSubtractorAtomic;

public class Adder implements Runnable {
    private SharedData data;

    public Adder(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            data.add();
        }
    }
}
