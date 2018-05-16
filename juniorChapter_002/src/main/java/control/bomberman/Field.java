package control.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Field.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 11.05.2018
 */
public class Field {
    private final ReentrantLock[][] board;
    private final int width;
    private final int height;

    public Field(final int width,final int height) {
        this.board = initBoard(height,width);
        this.width = width;
        this.height = height;
    }

    public boolean tryLock(Location location) throws InterruptedException {
        return this.board[location.getY()][location.getX()].tryLock(500, TimeUnit.MILLISECONDS);
    }

    public void unlock(Location location) {
        this.board[location.getY()][location.getX()].unlock();
    }

    private ReentrantLock[][] initBoard(int height, int width) {
        ReentrantLock[][] result = new ReentrantLock[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                result[i][j] = new ReentrantLock();
            }
        }
        return result;
    }
}
