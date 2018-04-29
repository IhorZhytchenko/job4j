package wait;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
/**
 * SimpleLock.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 29.04.2018
 */
@ThreadSafe
public class SimpleLock {
    @GuardedBy("this")
    private boolean state = false;
    @GuardedBy("this")
    private Thread current;

    public void lock() throws InterruptedException {
        synchronized (this) {
            while (this.state) {
                this.wait();
            }
            this.state = true;
            this.current = Thread.currentThread();
        }
    }
    public void unlock() {
        synchronized (this) {
            if (this.state && this.current == Thread.currentThread()) {
                this.current = null;
                this.state = false;
                this.notify();
            }
        }
    }
}
