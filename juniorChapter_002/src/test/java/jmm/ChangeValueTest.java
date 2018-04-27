package jmm;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * ChangeValueTest.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 27.04.2018
 */
public class ChangeValueTest {
    @Test
    public void showProblem() {
        A a = new A();
        Thread thread1 = new Thread(new ChangeValue(a));
        Thread thread2 = new Thread(new ChangeValue(a));
        Thread thread3 = new Thread(new ChangeValue(a));
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getValue());

    }
}