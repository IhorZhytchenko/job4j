package list;
/**
 * Cycle.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 11.04.2018
 */
public class Cycle {

    /**
     * Determines whether the list contains a loop.
     *
     * @param first the first element of the list
     * @return true if list contains a loop, or  false if list does not contain a loop
     */
    boolean hasCycle(final Node first) {
        Node turtle = first;
        Node hare = first;
        boolean result = false;
        while (true) {
            turtle = turtle.next;
            if (hare.next != null) {
                hare = hare.next.next;
            } else {
                break;
            }

            if (turtle == null || hare == null) {
                break;
            }
            if (turtle == hare) {
                result = true;
                break;
            }
        }
        return result;
    }


    public class Node<T> {
        T value;
        Node<T> next;
    }

}
