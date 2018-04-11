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
    private int index = -2;

    public EvenNumbersIterator(int[] values) {
        this.values = values;
    }

    /**
     * Method findEvenNumber.
     * @return Index of the next even number or -1 if the even number is not found
     */
    private int findEvenNumber() {
        int result = -1;
        for (int i = (this.index + 1); i < this.values.length; i++) {
           if ((this.values[i] % 2) == 0) {
               result = i;
               break;
           }
        }
        return result;
    }


    @Override
    public boolean hasNext() {
        if (this.index == -2) {
            this.index = -1;
            this.index = findEvenNumber();
        }
        return this.index != -1;
    }

    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = this.values[index];
        this.index = findEvenNumber();
        return result;
    }
}
