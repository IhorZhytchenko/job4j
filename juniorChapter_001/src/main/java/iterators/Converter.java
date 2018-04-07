package iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Converter.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 07.04.2018
 */
public class Converter {

    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {
            private final Iterator<Iterator<Integer>> iterators = it;
            private Iterator<Integer> currentIterator = null;

            private void checkIterator() {
                if (currentIterator == null || (currentIterator != null && !currentIterator.hasNext())) {
                    currentIterator = null;
                    while (iterators.hasNext()) {
                        Iterator<Integer> nextIterator = iterators.next();
                        if (nextIterator.hasNext()) {
                            currentIterator = nextIterator;
                            break;
                        }
                    }
                }
            }

            @Override
            public boolean hasNext() {
                checkIterator();
                return currentIterator != null;
            }

            @Override
            public Integer next() {
                checkIterator();
                if (currentIterator == null) {
                    throw new NoSuchElementException();
                }
                return currentIterator.next();
            }
        };
    }
}