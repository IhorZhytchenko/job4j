package control.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Monster.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 11.05.2018
 */
public class Monster extends Thread {
   private final Field field;
   private final int fieldWidth;
   private final int fieldHeight;
   private Location location;
   private Random random = new Random();

   public Monster(final Field field, final int width, final int height) {
       this.field = field;
       this.fieldHeight = height;
       this.fieldWidth = width;
   }


    @Override
    public void run() {
        try {
            this.startLocation();
            while (!isInterrupted()) {
                List<Location> locations = this.getNewLocations();
                Location newLoc = locations.get(this.random.nextInt(locations.size()));
                while (!this.field.tryLock(newLoc)) {
                    newLoc = locations.get(this.random.nextInt(locations.size()));
                }
                this.field.unlock(this.location);
                this.location = newLoc;
                System.out.println(Thread.currentThread().getName() + " step - " + location.toString());
                Thread.sleep(1000);

            }
        } catch (InterruptedException e) {
            this.field.unlock(this.location);
            e.printStackTrace();
        }
    }

    private List<Location> getNewLocations() {
        List<Location> result = new ArrayList<>();
        int x = this.location.getX();
        int y = this.location.getY();
        if (x > 0) {
            result.add(new Location(y,x - 1));
        }
        if (y > 0) {
            result.add(new Location(y - 1,x));
        }
        if (x < (this.fieldWidth - 1)) {
            result.add(new Location(y,x + 1));
        }
        if (y < (this.fieldHeight - 1)) {
            result.add(new Location(y + 1,x));
        }
        return result;
    }

    private void startLocation() throws InterruptedException {
        Location start = new Location(this.random.nextInt(this.fieldHeight),this.random.nextInt(this.fieldWidth ));
        while (!this.field.tryLock(start)) {
            start = new Location(this.random.nextInt(this.fieldHeight),this.random.nextInt(this.fieldWidth));
        }
        this.location = start;
        System.out.println(Thread.currentThread().getName() + "start - " + location.toString());
    }
}
