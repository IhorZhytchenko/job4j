package wait;

public class Main {
    private class ThreadProducer extends Thread {

        private final SimpleBlockingQueue<Integer> queue;

        private ThreadProducer(final SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run()
        {
            try {
                for (int i = 0; i < 100; i++) {
                    this.queue.offer(i);
                    System.out.println(i + " - добавлено в очередь, размер очереди - "  + this.queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    private class ThreadConsumer extends Thread {
        private final SimpleBlockingQueue<Integer> queue;

        private ThreadConsumer(final SimpleBlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run()
        {
            for (int i = 0; i < 100; i++) {
                try {
                    int a = this.queue.poll();
                    System.out.println(a + " - забрано из очереди, размер очереди - "  + this.queue.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    void start(){
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        new ThreadConsumer(queue).start();
        new ThreadProducer(queue).start();
    }
}
