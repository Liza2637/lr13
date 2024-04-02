import java.util.LinkedList;
import java.util.Queue;

public class Main3 {
    public static void main(String[] args) {
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 3;
        Thread producer = new Thread(new Producer(buffer, maxSize), "Producer");
        Thread consumer = new Thread(new Consumer(buffer), "Consumer");
        producer.start();
        consumer.start();
    }
}

class Producer implements Runnable {
    private final Queue<Integer> buffer;
    private final int maxSize;

    public Producer(Queue<Integer> buffer, int maxSize) {
        this.buffer = buffer;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (buffer) {
                while (buffer.size() == maxSize) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer.add(i);
                System.out.println("Производитель " + i);
                buffer.notifyAll();
            }
        }
    }
}

class Consumer implements Runnable {
    private final Queue<Integer> buffer;

    public Consumer(Queue<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (buffer) {
                while (buffer.isEmpty()) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                int data = buffer.poll();
                System.out.println("Потребитель " + data);
                buffer.notifyAll();
            }
        }
    }
}
