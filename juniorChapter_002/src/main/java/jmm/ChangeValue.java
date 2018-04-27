package jmm;
/**
 * class ChangeValue.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 27.04.2018
 */
public class ChangeValue implements Runnable {
    private A a;

    public ChangeValue(A a) {
        this.a = a;
    }

    @Override
    public void run() {
        int value = this.a.getValue();
        this.a.setValue(value + 5);
    }
}
