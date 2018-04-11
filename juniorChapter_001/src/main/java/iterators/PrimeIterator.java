package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * PrimeIterator.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 07.04.2018
 */
public class PrimeIterator implements Iterator {
    private final  int[] values;
    private int index = -2;

    public PrimeIterator(int[] values) {
        this.values = values;
    }

    /**
     * Method findPrimeNumber.
     * @return Index of the next prime number or -1 if the even number is not found
     */
    private int findPrimeNumber() {
        int result = -1;
        for (int i = (index + 1); i < values.length; i++) {
            boolean prime = true;
            if (values[i] == 1) {
                prime = false;
            }
           for (int j = 2; j < values[i]; j++) {
               if (values[i] % j == 0) {
                   prime = false;
                   break;
               }
           }
           if (prime) {
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
            this.index = findPrimeNumber();
        }
        return index != -1;
    }

    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        int result = values[index];
        this.index = findPrimeNumber();
        return result;
    }
}
