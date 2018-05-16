package control.bomberman;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * Bomberman.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 11.05.2018
 */
public class Bomberman extends Thread {
    private BlockingQueue<Runnable> move = new ArrayBlockingQueue<Runnable>(100);
    private final Field field;
    private final int fieldWidth;
    private final int fieldHeight;
    private Location location;
    private Random random = new Random();

    public Bomberman(final Field field, final int width, final int height) {
        this.field = field;
        this.fieldHeight = height;
        this.fieldWidth = width;
    }

    public void up() {
        if (this.location != null) {
            if (this.location.getY() < (this.fieldHeight - 1)) {
                Location nextLoc = new Location(Bomberman.this.location.getY() + 1,Bomberman.this.location.getX());
                this.step(nextLoc);
            }
        }
    }

    public void down() {
        if (this.location != null) {
            if (this.location.getY() > 0) {
                Location nextLoc = new Location(Bomberman.this.location.getY() - 1,Bomberman.this.location.getX());
                this.step(nextLoc);

            }
        }
    }

    public void left() {
        if (this.location != null) {
            if (this.location.getX() > 0) {
                Location nextLoc = new Location(Bomberman.this.location.getY(),Bomberman.this.location.getX() - 1);
                this.step(nextLoc);
            }
        }
    }
    public void right() {
        if (this.location != null) {
            if (this.location.getX() < (this.fieldWidth - 1)) {
                Location nextLoc = new Location(Bomberman.this.location.getY(),Bomberman.this.location.getX() + 1);
                this.step(nextLoc);
            }
        }
    }

    @Override
    public void run() {
        try {
            this.startLocation();
            while (!isInterrupted()) {
                Runnable step = this.move.poll();
                if (step != null) {
                    step.run();
                }
            }
        } catch (InterruptedException e) {
            this.field.unlock(this.location);
            e.printStackTrace();
        }
    }

    private void startLocation() throws InterruptedException {
        Location start = new Location(this.random.nextInt(this.fieldHeight),this.random.nextInt(this.fieldWidth ));
        while (!this.field.tryLock(start)) {
            start = new Location(this.random.nextInt(this.fieldHeight),this.random.nextInt(this.fieldWidth));
        }
        this.location = start;
        System.out.println("bomb" + " start - " + location.toString());
    }

     private void step(final Location nextLoc) {
         this.move.offer(() -> {
             try {
                 if (Bomberman.this.field.tryLock(nextLoc)) {
                     Bomberman.this.field.unlock(Bomberman.this.location);
                     Bomberman.this.location = nextLoc;
                     System.out.println("bomb" + " step - " + Bomberman.this.location.toString());
                 }
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }

         });
     }
}
