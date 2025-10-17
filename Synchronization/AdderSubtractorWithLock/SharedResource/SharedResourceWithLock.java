package Synchronization.AdderSubtractorWithLock.SharedResource;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedResourceWithLock {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void add() {
        lock.lock();

        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void subtractor() {
        lock.lock();
        try {
            count--;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        try {
            lock.lock();
            return count;
        } finally {
            lock.unlock();
        }
    }
}
