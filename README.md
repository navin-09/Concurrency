# flow of the process from code.

[Source Code (.java)]
         |
       javac
         v
[Bytecode (.class)]
         |
       jar/pack
         v
[Executable Archive (.jar)]
         |
     java -jar
         v
   [JVM Instance]
         |
   (running in OS)
         v
   [OS Process: java]
         |
      Threads (Thread-1, Thread-2, ...)
         |
     Concurrency / Parallelism


# process

| Concept       | Description                                       | Example                              |
| ------------- | ------------------------------------------------- | ------------------------------------ |
| **Process**   | A running instance of a program managed by the OS | JVM running your Java app            |
| **Program**   | Static set of instructions on disk                | `MyApp.jar`                          |
| **Thread**    | Execution unit within a process                   | `Thread-0`, `main`                   |
| **Isolation** | Each process has its own memory space             | JVM separate from Chrome             |
| **Lifecycle** | Created → Running → Waiting → Terminated          | `java` starts and exits after main() |


# thread 
| Concept              | Description                                                       | Example / Note                  |
| -------------------- | ----------------------------------------------------------------- | ------------------------------- |
| **Thread**           | Smallest unit of CPU execution within a process                   | Java’s `Thread` or `Runnable`   |
| **Main Thread**      | Runs `main()` method                                              | Always created by JVM           |
| **Thread Lifecycle** | NEW → RUNNABLE → RUNNING → WAITING → TERMINATED                   | Controlled by JVM scheduler     |
| **Shared Memory**    | Threads share heap; have separate stacks                          | Can lead to race conditions     |
| **Concurrency**      | Multiple threads executing logically at the same time             | Useful for multitasking         |
| **Parallelism**      | Threads executing *physically* at the same time on multiple cores | True simultaneous execution     |
| **Thread Cost**      | Lightweight compared to processes                                 | But not free — manage carefully |


# Threads vs Processes

| Feature            | **Process**                             | **Thread**                           |
| ------------------ | --------------------------------------- | ------------------------------------ |
| **Memory Space**   | Each process has its own memory         | Threads share memory                 |
| **Creation Cost**  | High (OS allocates new memory)          | Low (shares existing memory)         |
| **Communication**  | Via IPC (files, sockets)                | Direct via shared variables          |
| **Failure Impact** | One process crash doesn’t affect others | One bad thread can crash the process |
| **Concurrency**    | Multi-process parallelism               | Multi-threaded concurrency           |


# Concurrency vs Parallelism

| Concept         | Definition                                                                                                                                |
| --------------- | ----------------------------------------------------------------------------------------------------------------------------------------- |
| **Concurrency** | Doing *multiple things at once* (structurally overlapping tasks). It’s about *dealing with* many tasks at once.                           |
| **Parallelism** | Doing *multiple things literally at the same time* (simultaneously using multiple CPUs/cores). It’s about *executing* many tasks at once. |

# Analogy

| Scenario                                          | Concurrency                                                                      | Parallelism                                                         |
| ------------------------------------------------- | -------------------------------------------------------------------------------- | ------------------------------------------------------------------- |
| 🧍‍♂️ One person cooking and talking on the phone | Switching between cooking and talking (one at a time, but tasks overlap in time) | Two people — one cooks, one talks (both actually happening at once) |
| 🧵 In code                                        | One thread interleaving tasks                                                    | Multiple threads on different cores truly executing simultaneously  |


# 🧱 Basic Thread Program Flow in Java

| Step  | Code Element                       | Description                                                     | Example Snippet                                                  |
| ----- | ---------------------------------- | --------------------------------------------------------------- | ---------------------------------------------------------------- |
| **1** | **Define a Task**                  | Create a class that implements `Runnable` (or extend `Thread`). | `class MyTask implements Runnable { public void run() { ... } }` |
| **2** | **Create a Thread Object**         | Pass the task to a `Thread` instance.                           | `Thread t = new Thread(new MyTask());`                           |
| **3** | **Start the Thread**               | Call `start()` to tell JVM to run `run()` in a new thread.      | `t.start();`                                                     |
| **4** | **(Optional) Name Thread**         | Helps identify threads in logs/debugging.                       | `new Thread(new MyTask(), "Worker-1");`                          |
| **5** | **(Optional) Synchronize or Wait** | Use `join()` to wait for thread completion.                     | `t.join();`                                                      |
| **6** | **End of Execution**               | Thread terminates automatically when `run()` completes.         | —                                                                |


| Feature              | `Runnable`                            | `Callable<V>`                         |
| -------------------- | ------------------------------------- | ------------------------------------- |
| **Return value**     | Cannot return anything (`void run()`) | Returns a value (`V call()`)          |
| **Exceptions**       | Cannot throw checked exceptions       | Can throw checked exceptions          |
| **Method name**      | `run()`                               | `call()`                              |
| **Result retrieval** | Not possible                          | Via `Future.get()`                    |
| **Common use**       | Fire-and-forget tasks                 | Tasks that compute and return results |


# Race Condition ( adder subtracter problem.)

      This is a race condition — threads "race" to read/write a shared value.

| Feature / Aspect                                  | `synchronized`                                        | `ReentrantLock`                                                           |
| ------------------------------------------------- | ----------------------------------------------------- | ------------------------------------------------------------------------- |
| **Type**                                          | Keyword (language-level)                              | Class (`java.util.concurrent.locks`)                                      |
| **Reentrancy**                                    | ✅ Yes (reentrant)                                     | ✅ Yes (reentrant)                                                         |
| **Lock acquisition**                              | Automatic when entering a synchronized block/method   | Manual via `lock()` and `unlock()`                                        |
| **Lock release**                                  | Automatic when method/block exits (even on exception) | Must be released manually in `finally` block                              |
| **Try to acquire lock (non-blocking)**            | ❌ Not possible                                        | ✅ `tryLock()` / `tryLock(timeout)`                                        |
| **Interruptible lock acquisition**                | ❌ No                                                  | ✅ `lockInterruptibly()`                                                   |
| **Condition variables (wait/notify alternative)** | `wait()`, `notify()`, `notifyAll()`                   | `Condition` objects (`newCondition()`)                                    |
| **Performance**                                   | Slightly faster for simple locking                    | More flexible but a bit heavier                                           |
| **Visibility (memory consistency)**               | Automatically handles visibility (like `volatile`)    | Also handles visibility via lock/unlock                                   |
| **Debugging / Monitoring**                        | Limited (no info on lock state)                       | ✅ Methods like `isLocked()`, `getHoldCount()`, `isHeldByCurrentThread()`  |


TIME →
┌────────────────────────────────────────────────────────────┐
│                          SharedResource                    │
│  count = 0                                                 │
│  lock = ReentrantLock@1234  (shared between both threads)  │
└────────────────────────────────────────────────────────────┘

   ┌──────────────────────┐         ┌──────────────────────┐
   │     Adder Thread     │         │  Subtractor Thread   │
   └──────────────────────┘         └──────────────────────┘
             │                                  │
             │ lock.lock()                      │
             │  (lock free → acquire)           │
             │────────────────────────────────>│
             │ count++ (critical section)       │
             │                                  │
             │ lock.unlock()                    │
             │ releases → notifies waiting      │
             │                                  │
             │                                  │ lock.lock()
             │                                  │ (now free → acquire)
             │                                  │
             │                                  │ count-- (critical section)
             │                                  │
             │                                  │ lock.unlock()
             │                                  │ (release)


# critical section

      A critical section is a part of a program where shared data is accessed or modified, and therefore only one thread must execute it at a time.

# What is preemptiveness?

      Preemptive multitasking means the operating system can interrupt (preempt) a running thread at any time to give CPU time to another thread.
      
      In simpler words:
            Threads don’t politely take turns — the OS forces them to share the CPU fairly.

# What is context switching?

      Context switching is the process of saving the state of one thread (registers, program counter, stack, etc.) and loading the state of another thread — so the CPU can continue executing that one.

# What happens during a context switch (under the hood)

      1. Save current thread’s state

            a. CPU registers (like program counter, stack pointer)

            b. Thread ID, scheduling info

      2. Select next runnable thread (based on priority, fairness, etc.)

      3. Load next thread’s state

      4. CPU starts executing the new thread’s instructions


# Deadlock
      A deadlock is a situation in multithreading where two or more threads are permanently blocked,
      each waiting for a resource (lock, monitor, etc.) that the other thread holds,
      and none of them can proceed.

| Aspect                               | Description                                                                                                                                                                                                                    |
| ------------------------------------ | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **Definition**                       | A state where threads are waiting on each other forever, preventing progress                                                                                                                                                   |
| **Main Cause**                       | Multiple locks acquired in **different orders** by different threads                                                                                                                                                           |
| **Necessary Conditions** (Coffman’s) | 1️⃣ *Mutual exclusion* (locks are exclusive)<br>2️⃣ *Hold and wait* (threads hold one resource while waiting for another)<br>3️⃣ *No preemption* (locks can’t be forcibly taken)<br>4️⃣ *Circular wait* (thread A → B → C → A) |
| **Symptoms**                         | Program hangs, high CPU idle, no exceptions thrown                                                                                                                                                                             |
| **Common Example**                   | Thread 1 locks `lockA` then waits for `lockB`; Thread 2 locks `lockB` then waits for `lockA`                                                                                                                                   |
| **In Java**                          | Happens with `synchronized`, `ReentrantLock`, or any blocking resource if lock ordering is inconsistent                                                                                                                        |
| **Detection**                        | Using `ThreadMXBean.findDeadlockedThreads()` or thread dumps (`jstack`)                                                                                                                                                        |
| **Prevention Techniques**            | ✅ Use **consistent lock order** across all threads<br>✅ Use **tryLock(timeout)** to back off<br>✅ Avoid nested locks when possible<br>✅ Prefer **higher-level concurrent utilities** (`ConcurrentHashMap`, atomics)            |
| **Difference from Livelock**         | Deadlock → threads are stuck doing *nothing*.<br>Livelock → threads are *active* but keep changing state without progress.                                                                                                     |
| **Typical Fix**                      | Acquire locks in the **same sequence** everywhere (e.g., always `lock1 → lock2`)                                                                                                                                               |
# What is a Semaphore?

      A semaphore is a synchronization mechanism that controls access to a shared resource by using a counter.

      It’s like a “gatekeeper” — it decides how many threads are allowed to enter a critical section.

      Simple analogy

            Think of a parking lot with 3 spots (permits):

                  3 cars can park (3 permits).

                  If the lot is full (0 permits left), other cars must wait.

                  When one car leaves (releases), another can park.

# Types of Semaphores

| Type                   | Description                                | Java Class         |
| ---------------------- | ------------------------------------------ | ------------------ |
| **Binary Semaphore**   | Only 0 or 1 permit → acts like a **mutex** | `new Semaphore(1)` |
| **Counting Semaphore** | Allows *n* concurrent threads              | `new Semaphore(n)` |

# What is a Mutex?
      A mutex (Mutual Exclusion) is a lock that allows only one thread at a time to access a resource.


# Producer–Consumer Problem (Semaphore Version).
      The Producer–Consumer Problem is a classic example of multi-thread synchronization in operating systems and concurrent programming.

      It describes two types of threads — Producers and Consumers — that share a fixed-size buffer.

# Problem Definition
      Producer threads generate data (items) and place them into a shared buffer.

      Consumer threads remove items from that buffer for processing.

      The buffer has a limited capacity (can hold only n items at once).

      Both must work without stepping on each other’s data and without race conditions.

# The Challenge
      A producer might try to add an item when the buffer is full → causes overflow.

      A consumer might try to remove an item when the buffer is empty → underflow.

      Both could access the buffer simultaneously, causing inconsistent data.
      
# The Semaphore Solution

| Semaphore | Purpose                                                                                      | Initial Value            |
| --------- | -------------------------------------------------------------------------------------------- | ------------------------ |
| `empty`   | Counts how many buffer slots are **free**                                                    | buffer capacity (e.g. 3) |
| `full`    | Counts how many items are **ready to consume**                                               | 0                        |
| `mutex`   | A **binary lock** (mutual exclusion) — ensures only one thread accesses the buffer at a time | 1                        |

# Flow of Execution

          ┌────────────────────────────┐
          │        Shared Buffer       │
          │  (Fixed Capacity Queue)    │
          └────────────┬───────────────┘
                       │
    ┌──────────────────┼──────────────────┐
    │                                      │
Producer Thread(s)                   Consumer Thread(s)
──────────────────                   ──────────────────
1️⃣ Wait (empty--)                    1️⃣ Wait (full--)
2️⃣ Lock (mutex--)                    2️⃣ Lock (mutex--)
3️⃣ Add item                          3️⃣ Remove item
4️⃣ Unlock (mutex++)                  4️⃣ Unlock (mutex++)
5️⃣ Signal (full++)                   5️⃣ Signal (empty++)

# Key Points Summary

| Concept                   | Description                                                                     |
| ------------------------- | ------------------------------------------------------------------------------- |
| **Goal**                  | Coordinate producers and consumers so they don’t conflict when sharing a buffer |
| **Issue Solved**          | Prevents overflow, underflow, and race conditions                               |
| **Synchronization Tools** | Semaphores (`empty`, `full`, `mutex`)                                           |
| **Critical Section**      | The code that modifies the shared buffer (add/remove item)                      |
| **Mutex Role**            | Ensures only one thread accesses the buffer at a time                           |
| **Empty / Full**          | Control when threads wait or proceed based on buffer state                      |
| **Result**                | Safe, deadlock-free producer-consumer interaction                               |

