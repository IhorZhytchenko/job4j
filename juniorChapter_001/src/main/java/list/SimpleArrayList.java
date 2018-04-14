package list;

import generic.SimpleList;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * SimpleArrayList.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 10.04.2018
 */
public class SimpleArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] container;
    private int size;
    private int modCount = 0;
    /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public SimpleArrayList() {
        this.container =  new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
    /**
     * Constructs an empty list with the specified initial capacity.
     * @param capacity the initial length of the list
     */
    public SimpleArrayList(int capacity) {
        if (capacity >= 0) {
            this.container = new Object[capacity];
        } else {
            this.container =  new Object[DEFAULT_CAPACITY];
        }
        this.size = 0;
    }
    /**
     * Appends the specified element to the end of this list.
     *
     * @param value element to be appended to this list
     */
    public void add(T value) {
        this.ensureCapacity(size + 1);
        this.container[size++] = value;
        this.modCount++;
    }
    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return this.size;
    }
    /**
     * Returns the element at the specified position in this list.
     *
     * @param  index index of the element to return
     * @return the element at the specified position in this list
     */
    public T get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (T) this.container[index];
    }
    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private void ensureCapacity(int capacity) {
        if (capacity >= this.container.length) {
            int newCapacity;
            if (this.container.length < DEFAULT_CAPACITY) {
                newCapacity = DEFAULT_CAPACITY;
            } else {
                newCapacity = (this.container.length * 3) / 2 + 1;
            }
            this.container = Arrays.copyOf(this.container, newCapacity);
        }

    }

    private class Itr implements Iterator<T> {
        int cursor = 0;
        int expectedModCount = SimpleArrayList.this.modCount;

        Itr() {

        }

        @Override
        public boolean hasNext() {
            return this.cursor < SimpleArrayList.this.size;
        }

        @Override
        public T next() {
            if (this.expectedModCount != SimpleArrayList.this.modCount) {
                throw new ConcurrentModificationException();
            }
            Object[] values = SimpleArrayList.this.container;
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) values[cursor++];
        }
    }
}
