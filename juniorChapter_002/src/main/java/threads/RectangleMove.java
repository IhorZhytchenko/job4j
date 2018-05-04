package threads;

import javafx.scene.shape.Rectangle;
/**
 * RectangleMove.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 27.04.2018
 */
public class RectangleMove implements Runnable {
    private static final double SIZE_X = 310.0;
    private final Rectangle rect;
    private int step = 1;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        Thread current = Thread.currentThread();
        while (!current.isInterrupted()) {
            this.move();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void move() {
        border();
        this.rect.setX(this.rect.getX() + this.step);
    }

    private void border() {
        if (this.step == 1) {
            if ((this.rect.getX() + this.rect.getWidth()) >= SIZE_X) {
                this.step = -1;
            }
        }
        if (this.step == -1) {
            if (this.rect.getX() <= 0) {
                this.step = 1;
            }
        }
    }

}