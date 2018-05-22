package control;

import java.util.concurrent.CountDownLatch;

/**
 * DeadLock.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 07.05.2018
 */
public class DeadLock {
    private final CountDownLatch latch = new CountDownLatch(2);

    public void doSomething(Object o1, Object o2) {
        synchronized (o1) {
            this.latch.countDown();
            try {
                this.latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - ждет освобождения второго ресурса");
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " - занял второй ресурс");
            }
        }
    }

    public void createDeadlock() {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Thread thread1 = new Thread(() -> DeadLock.this.doSomething(lock1,lock2));
        Thread thread2 = new Thread(() -> DeadLock.this.doSomething(lock2,lock1));
        thread1.start();
        thread2.start();
    }
}
