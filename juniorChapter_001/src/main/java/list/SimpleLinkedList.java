package list;

import java.util.*;

/**
 * SimpleLinkedList.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 10.04.2018
 */
public class SimpleLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> first;
    private Node<T> last;
    /**
     * Constructs an empty list.
     */
    public SimpleLinkedList() {
    }
    /**
     * Appends the specified element to the end of this list.
     *
     * @param value element to be appended to this list
     */
    public void add(T value) {
        final Node<T> l = this.last;
        final Node<T> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            this.first = newNode;
        } else {
            l.next = newNode;
        }
        this.size++;
        this.modCount++;
    }
    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    public T get(int index) {
        return node(index).item;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Returns the  Node at the specified element index.
     */
    private   Node<T> node(int index) {
        if ((index < 0) || (index >= size)) {
           throw new IndexOutOfBoundsException();
        }
        if (index < (size / 2)) {
            Node<T> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<T> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
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

    private class Itr implements Iterator<T> {
        private Node<T> lastReturned;
        private Node<T> next = SimpleLinkedList.this.first;
        private int nextIndex = 0;
        int expectedModCount = SimpleLinkedList.this.modCount;

        Itr() {
        }

        @Override
        public boolean hasNext() {
            return nextIndex < SimpleLinkedList.this.size;
        }

        @Override
        public T next() {
            if (this.expectedModCount != SimpleLinkedList.this.modCount) {
                throw new ConcurrentModificationException();
            }
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }
    }
    /**
     * Two connected node.
     */
    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> prev;

        Node(Node<T> prev, T element, Node<T> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}