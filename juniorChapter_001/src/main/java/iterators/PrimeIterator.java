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
    private int index = -1;

    public PrimeIterator(int[] values) {
        this.values = values;
        index = findPrimeNumber();
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
        return index != -1;
    }

    @Override
    public Object next() {
        if (index == -1) {
            throw new NoSuchElementException();
        }
        int result = values[index];
        index = findPrimeNumber();
        return result;
    }
}
