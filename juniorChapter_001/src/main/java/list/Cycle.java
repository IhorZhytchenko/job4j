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
        Cycle.Node f = first;
        int count = 0;
        boolean result = false;
        while (f.next != null) {
            f = f.next;
            count++;
            Cycle.Node node = first;
            for (int i = 0; i < count; i++) {
                if (node == f) {
                    result = true;
                    break;
                }
                node = node.next;
            }
            if (result) {
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
