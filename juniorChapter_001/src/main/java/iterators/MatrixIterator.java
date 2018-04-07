package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * MatrixIterator.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 03.04.2018
 */
public class MatrixIterator implements Iterator {

    private final int[][] values;
    private int indexI = 0;
    private int indexJ = 0;

    public MatrixIterator(int[][] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        return indexI < values.length;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int result = values[indexI][indexJ];
        if ((indexJ + 1) == values[indexI].length) {
            indexI++;
            indexJ = 0;
        } else {
            indexJ++;
        }
        return result;
    }
}
