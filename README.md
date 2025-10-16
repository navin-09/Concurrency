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
