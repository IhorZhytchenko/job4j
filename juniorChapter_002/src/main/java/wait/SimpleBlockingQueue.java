package wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
/**
 * SimpleBlockingQueue.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.04.2018
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int maxSize;
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();


    public SimpleBlockingQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (this) {
            while (this.size() >= this.maxSize) {
                    this.wait();
            }
            this.queue.offer(value);
            this.notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (this) {
            while (this.size() == 0) {
                this.wait();
            }
            T result = this.queue.poll();
            this.notify();
            return result;
        }

    }

    public int size() {
        return this.queue.size();
    }
}
