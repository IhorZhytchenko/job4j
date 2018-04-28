package wait;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * SimpleThreadPool.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.04.2018
 */
public class SimpleThreadPool {

    private BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(100);
    private volatile boolean isRunning = true;

    public SimpleThreadPool() {
        int count = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < count; i++) {
            new Thread(new TaskWorker()).start();
        }
    }

    public void add(Runnable work) {
        if (isRunning) {
            this.taskQueue.offer(work);
        }
    }

    public void shutdown() {
        this.isRunning = false;
    }

    private final class TaskWorker implements Runnable {
        @Override
        public void run() {
            while (SimpleThreadPool.this.isRunning) {
                Runnable task = SimpleThreadPool.this.taskQueue.poll();
                if (task != null) {
                    task.run();
                }
            }
        }
    }
}
