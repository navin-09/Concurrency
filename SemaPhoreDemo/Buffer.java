package SemaPhoreDemo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 * Shared bounded buffer protected by semaphores:
 * - empty: number of free slots
 * - full: number of items available
 * - mutex: binary semaphore for mutual exclusion
 */
public class Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity;

    private final Semaphore empty;
    private final Semaphore full;
    private final Semaphore mutex;

    public Buffer(int capacity) {
        this.capacity = capacity;
        this.empty = new Semaphore(capacity); // start with all slots free
        this.full = new Semaphore(0); // start with no items
        this.mutex = new Semaphore(1); // binary semaphore for mutual exclusion
    }

    // Producer calls this to put an item into the buffer
    public void put(int item) throws InterruptedException {
        empty.acquire(); // wait for a free slot
        mutex.acquire(); // enter critical section

        try {
            queue.add(item);
            System.out
                    .println(Thread.currentThread().getName() + " produced: " + item + " (size=" + queue.size() + ")");
        } finally {
            mutex.release(); // leave critical section
            full.release(); // signal there's an additional full slot
        }
    }

    // Consumer calls this to take an item from the buffer
    public int take() throws InterruptedException {
        full.acquire(); // wait for item to be available
        mutex.acquire(); // enter critical section

        try {
            int item = queue.remove();
            System.out
                    .println(Thread.currentThread().getName() + " consumed: " + item + " (size=" + queue.size() + ")");
            return item;
        } finally {
            mutex.release(); // leave critical section
            empty.release(); // signal there's an additional empty slot
        }
    }
}
