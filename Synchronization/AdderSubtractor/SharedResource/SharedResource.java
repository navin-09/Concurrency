package Synchronization.AdderSubtractor.SharedResource;

public class SharedResource {
    int count = 0;

    public void add() {
        count++;
    }

    public void subtract() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
