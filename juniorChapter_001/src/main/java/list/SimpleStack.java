package list;
/**
 * SimpleStack.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 11.04.2018
 */
public class SimpleStack<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;
    /**
     * Retrieves and removes the last element of this list.
     *
     * @return the last element of this list, or  null if this list is empty
     */
    public T poll() {
        final Node<T> l = last;
        if (l == null) {
            return null;
        } else {
            final T element = l.item;
            final Node<T> prev = l.prev;
            l.item = null;
            l.prev = null;
            last = prev;
            if (prev == null) {
                first = null;
            } else {
                prev.next = null;
            }
            size--;
            return element;
        }

    }
    /**
     * Appends the specified element to the end of this list.
     *
     * @param value element to be appended to this list
     */
    public void push(T value) {
        final Node<T> l = this.last;
        final Node<T> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            this.first = newNode;
        } else {
            l.next = newNode;
        }
        this.size++;
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
