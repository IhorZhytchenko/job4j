package control;
/**
 * Switch.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 07.05.2018
 */
public class Switch {
    private StringBuffer stringBuffer = new StringBuffer();
    private volatile boolean label = true;
    private volatile boolean cancel = false;

    public void addValue(int value) {
        this.stringBuffer.append(value);
    }

    public void switcher() {
        Thread thread1 = new Thread(() -> {
            while (!Switch.this.cancel) {
                while (!Switch.this.label) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 10; i++) {
                    Switch.this.addValue(1);
                }
                Switch.this.label = false;
            }

        });

        Thread thread2 = new Thread(() -> {
            while (!Switch.this.cancel) {
                while (Switch.this.label) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < 10; i++) {
                    Switch.this.addValue(2);
                }
                Switch.this.label = true;
            }

        });
        thread1.start();
        thread2.start();
    }

    public void stopSwitcher() {
        this.cancel = true;
    }

    public String getString() {
        return this.stringBuffer.toString();
    }
}
