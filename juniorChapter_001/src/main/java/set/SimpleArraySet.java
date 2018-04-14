package set;

import list.SimpleArrayList;

import java.util.Iterator;
/**
 * SimpleArraySet.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 14.04.2018
 */
public class SimpleArraySet<E> implements Iterable<E> {
   private SimpleArrayList<E> list;

   public SimpleArraySet() {
       this.list = new SimpleArrayList<E>();
   }
    /**
     * Adds the specified element to this set if it is not already present.
     *
     * @param e element to be added to this set
     */
   public void add(E e) {
       if (check(e)) {
           this.list.add(e);
       }
   }
    /**
     * Checks if there is an element in the set.
     *
     * @param e element to be checked
     * @return true if the element is absent in the set, false if an element is present in a set
     */
    private boolean check(E e) {
       boolean result = true;
       for (E value : this.list) {
           if (value.equals(e)) {
               result = false;
               break;
           }
       }
       return result;
    }


    @Override
    public Iterator<E> iterator() {
        return this.list.iterator();
    }
}
