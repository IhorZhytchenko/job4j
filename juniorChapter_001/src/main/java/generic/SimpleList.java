package generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleList.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 09.04.2018
 */
public class SimpleList<T> implements Iterable<T> {
    Object[] objects;
    int size;
    /**
     * Constructs an empty list with the specified initial length.
     * @param length the initial length of the list
     */
    public SimpleList(int length) {
        this.objects = new Object[length];
    }
    /**
     * Appends the specified element to the end of this list.
     *
     * @param model element to be appended to this list
     */
    public void add(T model) {
        this.objects[this.size++] = model;
    }
    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     */
    public T get(int index) {
        return (T) this.objects[index];
    }
    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of the element to replace
     * @param model element to be stored at the specified position
     */
    public void set(int index, T model) {
        this.objects[index] = model;
    }
    /**
     * Removes the element at the specified position in this list.
     *
     * @param index the index of the element to be removed
     */
    public void delete(int index) {
        int numMoved = this.size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(this.objects, index + 1, this.objects, index, numMoved);
        }
        this.objects[--this.size] = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor = 0;

        Itr() {

        }

        @Override
        public boolean hasNext() {
            return this.cursor < SimpleList.this.size;
        }

        @Override
        public T next() {
            Object[] values = SimpleList.this.objects;
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) values[cursor++];
        }
    }
}
