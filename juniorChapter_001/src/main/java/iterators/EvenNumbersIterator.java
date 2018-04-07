package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * EvenNumbersIterator.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 07.04.2018
 */
public class EvenNumbersIterator implements Iterator {
    private final  int[] values;
    private int index = -1;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
        index = findEvenNumber();
    }

    /**
     * Method findEvenNumber.
     * @return Index of the next even number or -1 if the even number is not found
     */
    private int findEvenNumber() {
        int result = -1;
        for (int i = (index + 1); i < values.length; i++) {
           if ((values[i] % 2) == 0) {
               result = i;
               break;
           }
        }
        return result;
    }


    @Override
    public boolean hasNext() {
        return index != -1;
    }

    @Override
    public Object next() {
        if (index == -1) {
            throw new NoSuchElementException();
        }
        int result = values[index];
        index = findEvenNumber();
        return result;
    }
}
