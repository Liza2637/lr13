public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Во время выполнения: " + Thread.currentThread().getState());
            }
        });

        System.out.println("Перед запуском: " + thread.getState());
        thread.start();
        Thread.sleep(1000); // Пауза, чтобы убедиться, что поток начал выполнение
        System.out.println("После запуска: " + thread.getState());
    }
}
