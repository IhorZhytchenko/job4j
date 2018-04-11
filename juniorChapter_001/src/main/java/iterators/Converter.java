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
            private Iterator<Integer> itr = null;

            private void checkIterator() {
                if (itr == null || (itr != null && !itr.hasNext())) {
                    itr = null;
                    while (iterators.hasNext()) {
                        Iterator<Integer> iterator = iterators.next();
                        if (iterator.hasNext()) {
                            itr = iterator;
                            break;
                        }
                    }
                }
            }

            @Override
            public boolean hasNext() {
                checkIterator();
                return itr != null;
            }

            @Override
            public Integer next() {
                checkIterator();
                if (itr == null) {
                    throw new NoSuchElementException();
                }
                return itr.next();
            }
        };
    }
}