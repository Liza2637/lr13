public class Main2 {
    public static void main(String[] args) {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName());
                    lock.notify();
                    try {
                        if (i < 4) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Поток Петя");

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 5; i++) {
                    System.out.println(Thread.currentThread().getName());
                    lock.notify();
                    try {
                        if (i < 4) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Поток Вова");

        thread1.start();
        thread2.start();
    }
}
