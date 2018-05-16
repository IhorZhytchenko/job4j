package control.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * Game.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 11.05.2018
 */
public class Game {
    private final Field field;
    private final int width;
    private final int height;
    private final int mosterCount;
    private final int blockCount;
    private Bomberman bomberman;
    private List<Monster> monsters = new ArrayList<>();
    private List<Location> blocks = new ArrayList<>();
    private Random random = new Random();

    public Game(final int width, final int height, final int mosterCount, final int blockCount) {
        this.field = new Field(width, height);
        this.width = width;
        this.height = height;
        this.mosterCount = mosterCount;
        this.blockCount = blockCount;
    }

    public void start() throws InterruptedException {
        this.bomberman = new Bomberman(this.field, this.width, this.height);
        addBlocks();
        for (int i = 0; i < this.mosterCount; i++) {
            this.monsters.add(new Monster(this.field, this.width, this.height));
            this.monsters.get(i).start();
        }
        this.bomberman.start();

    }

    private void addBlocks() throws InterruptedException {
        for (int i = 0; i < this.blockCount; i++) {
            int x = random.nextInt(this.width);
            int y = random.nextInt(this.height);
            Location location = new Location(y,x);
            if (this.field.tryLock(location)) {
                this.blocks.add(location);
            }
        }
    }
}
