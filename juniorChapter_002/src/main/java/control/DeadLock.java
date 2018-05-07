package control;
/**
 * DeadLock.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 07.05.2018
 */
public class DeadLock {

    public void doSomething(Object o1, Object o2) {
        synchronized (o1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " - ждет освобождения второго ресурса");
            synchronized (o2) {

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
