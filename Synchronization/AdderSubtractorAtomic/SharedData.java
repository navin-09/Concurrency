package Synchronization.AdderSubtractorAtomic;

import java.util.concurrent.atomic.AtomicInteger;

public class SharedData {
    private AtomicInteger value = new AtomicInteger(0);

    public void add() {
        value.incrementAndGet(); // Atomic operation
    }

    public void subtract() {
        value.decrementAndGet(); // Atomic operation
    }

    public int getValue() {
        return value.get();
    }
}
