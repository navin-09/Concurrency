package Synchronization.AdderSubtractorWithSynchronization.SharedResource;

public class SharedResourceWithSynchronized {
    private int count = 0;

    public synchronized void add() {
        count++;
    }

    public synchronized void subtract() {
        count--;
    }

    public synchronized int getCount() {
        return count;
    }

}
