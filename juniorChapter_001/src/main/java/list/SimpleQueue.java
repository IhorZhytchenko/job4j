package list;
/**
 * SimpleQueue.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 11.04.2018
 */
public class SimpleQueue<T> {
    private SimpleLinkedList<T> list;

    public SimpleQueue() {
        this.list = new SimpleLinkedList<T>();
    }
    /**
     * Retrieves and removes the first element of this list.
     *
     * @return the first element of this list, or  null if this list is empty
     */
    public T poll() {
        final T element = this.list.get(0);
        this.list.remove(0);
        return element;
    }
    /**
     * Appends the specified element to the end of this list.
     *
     * @param value element to be appended to this list
     */
    public void push(T value) {
       this.list.add(value);
    }
}
