package list;
/**
 * SimpleStack.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 11.04.2018
 */
public class SimpleStack<T> {
    private SimpleLinkedList<T> list;

    public SimpleStack() {
        this.list = new SimpleLinkedList<T>();
    }
    /**
     * Retrieves and removes the last element of this list.
     *
     * @return the last element of this list, or  null if this list is empty
     */
    public T poll() {
        int index = this.list.size() - 1;
        final T element = this.list.get(index);
        this.list.remove(index);
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
