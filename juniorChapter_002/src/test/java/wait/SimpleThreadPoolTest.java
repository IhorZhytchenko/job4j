package wait;

import org.junit.Test;

import static org.junit.Assert.*;
/**
 * SimpleThreadPoolTest.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 28.04.2018
 */
public class SimpleThreadPoolTest {
    @Test
    public void poolTest()  {
        Runnable work = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        SimpleThreadPool pool = new SimpleThreadPool();
        for (int i = 0; i < 100; i++) {
            pool.add(work);
        }
    }

}