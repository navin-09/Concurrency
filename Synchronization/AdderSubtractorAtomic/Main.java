package Synchronization.AdderSubtractorAtomic;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SharedData data = new SharedData();

        Thread adder = new Thread(new Adder(data));
        Thread subtractor = new Thread(new Subtractor(data));

        adder.start();
        subtractor.start();

        adder.join();
        subtractor.join();

        System.out.println("Final value: " + data.getValue());
    }
}
